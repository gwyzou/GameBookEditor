package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.repositories.IRepository;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.views.interfaces.IBasicPresenter;
import org.helmo.gbeditor.views.ItemBook;
import org.helmo.gbeditor.presenters.interfaces.IViewToPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter associated to MainPageView
 */
public class MainPagePresenter implements IBasicPresenter {

    private transient IViewToPresenter mainPageView;
    private transient final IRepository repository;
    private transient final MainPresenter mainPresenter;

    /**
     * Constructor
     * @param repository
     */
    public MainPagePresenter(IRepository repository, MainPresenter mainPresenter) {
        this.repository = repository;
        this.mainPresenter=mainPresenter;
        mainPresenter.addPresenter(EnumPresentersNames.MAIN_PAGE_PRESENTER,this);
    }

    /**
     * getter of MainPresenter author
     * @return Author
     */
    public String getAuthor(){
        return String.valueOf(mainPresenter.getAuthor());
    }

    /**
     * Load all books from repository an transform them into item View Book
     * @return
     */
    public Iterable<ItemBook> getBookList() {
        List<ItemBook> bookList = new ArrayList<>();
        repository.loadAll()
                .forEach(book->bookList.add(
                        new ItemBook(book.getTitle(),book.getIsbn().toString(),book.getAuthor().toString(), book.getResume())
                ));
        return bookList;
    }

    /**
     * get all pages which are published
     * @return
     */
    public Iterable<ItemBook> getPublishedBook(){
        List<ItemBook> bookList = new ArrayList<>();
        repository.getPublished()
                .forEach(book->bookList.add(
                        new ItemBook(book.getTitle(),book.getIsbn().toString(),book.getAuthor().toString(), book.getResume())
                ));
        return bookList;
    }

    /**
     * Action when click on edit book
     * go to ViewCrEdBook with selection data
     * @param userData
     */
    public void editBook(Object userData) {
        if(userData==null){
            mainPresenter.onEditBook(null);
        }else if(userData instanceof ItemBook){
            String isbn = ((ItemBook) userData).getIsbn();
            try{
                Book toSend = repository.findBookWithIsbn(isbn);
                mainPresenter.onEditBook(toSend);
            }catch (RuntimeException e){
                mainPageView.showError("Couldn't find selected Book in data");
            }
        }else{
            mainPageView.showError("There is an Error in the program please try again");
        }
    }
    @Override
    public void onEditAuthor() {
        mainPageView.onEditAuthor(mainPresenter.getAuthor().toString());
    }

    @Override
    public void onDataReceived(Book data) {
    }

    @Override
    public void setView(IViewToPresenter basicView) {
        mainPageView= basicView;

    }

    @Override
    public void setViewVisible() {
        mainPageView.setVisible();
        if(!repository.getPublished().iterator().hasNext() && !repository.loadAll().iterator().hasNext()){
            mainPageView.showError("There is currently no books");
        }
    }

    /**
     * go to login presenter an display it's view
     */
    public void disconnect() {
        mainPresenter.goTo(EnumPresentersNames.LOGIN_PAGE_PRESENTER);
    }

    /**
     * button to publish book
     * @param itemBook
     */
    public void publishBook(ItemBook itemBook) {
        try{
            if(repository.findBookWithIsbn(itemBook.getIsbn()).getPageList().size()>0){

                repository.publishBook(itemBook.getIsbn());
                mainPageView.setVisible();
            }else{
                mainPageView.showError("Can't publish empty book");
            }
        }catch (RuntimeException e){
            mainPageView.showError("Couldn't Publish the book");
        }
    }
}
