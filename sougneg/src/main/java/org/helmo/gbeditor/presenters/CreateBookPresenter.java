package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.repositories.IRepository;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Isbn;
import org.helmo.gbeditor.views.interfaces.IBasicPresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewToCreateBookPresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewToPresenter;
import org.helmo.gbeditor.views.ItemBook;


/**
 * presented associated to viewCrEdBook
 */
public class CreateBookPresenter implements IBasicPresenter {
    private transient IViewToCreateBookPresenter createBookView;
    private transient final IRepository repository;
    private transient final MainPresenter mainPresenter;


    /**
     * create presenter associated to CreateBookView
     * @param repository
     */
    public CreateBookPresenter(IRepository repository, MainPresenter mainPresenter) {
        this.repository = repository;
        this.mainPresenter = mainPresenter;
        mainPresenter.addPresenter(EnumPresentersNames.CREATE_BOOK_PRESENTER,this);
    }

    /**
     * add new book to data
     * @param title
     * @param lang
     * @param bookNbr
     * @param resume
     */
    public void addBook(String title,String lang,String bookNbr,String resume) {
        try{
            if(mainPresenter.getInitialBook()!=null){
                mainPresenter.getInitialBook().setPrimaryData(title,resume,new Isbn(lang,mainPresenter.getAuthor().getMatricule(),bookNbr));
                repository.addBook(mainPresenter.getInitialBook());
            }else{
                Book currentBook=new Book(title,resume,new Isbn(lang,mainPresenter.getAuthor().getMatricule(),bookNbr),mainPresenter.getAuthor());
                currentBook.setPageList(mainPresenter.getPageList());
                repository.addBook(currentBook);
            }
            try{
                goTo(EnumPresentersNames.MAIN_PAGE_PRESENTER);
            }catch (RuntimeException e){
                createBookView.showError("Couldn't upload Book");
            }
        }catch (IllegalArgumentException e){
            checkValues(title,lang,bookNbr,resume);
        }
    }
    private void checkValues(String title,String lang,String bookNbr,String resume){
        String errorMessage="";
        errorMessage+=checkName(title);
        errorMessage+=checkResume(resume);
        errorMessage+=checkIsbn(lang,bookNbr);
        createBookView.showError(errorMessage);
    }
    private String checkIsbn(String lang,String bookNbr){
        String toReturn="";
        try{
            new Isbn(lang,mainPresenter.getAuthor().getMatricule(),bookNbr);
        }catch (IllegalArgumentException e){
                toReturn+=checkLang(lang);
                toReturn+=checkNbr(bookNbr);
        }
        return  toReturn;
    }

    private String checkNbr(String nbr) {
        if(!nbr.matches("^[0-9]{2}$")){
            return"\nBookNumber must be between 0 and 99 and with two digits ex: 01";
        }
        return "";
    }

    private String checkLang(String lang) {

        if(!lang.matches("^[0-9]$")){
            return"\nLanguage between 0 and 9";
        }
        return "";
    }
    private String checkName(String name){

        if (name.isBlank() || name.length() > 150) {
            return "\nName field has to be filled with 1 to 150 characters";
        }
        return "";
    }
    private String checkResume(String resume){

        if (resume.isBlank() ||resume.length() > 500) {
            return  "\nResume field has to be filled with 1 to 500 characters";
        }
        return "";
    }

    private void goTo(EnumPresentersNames viewName) {
        mainPresenter.goTo(viewName);
    }

    /**
     * go Back to MainPageView
     */
    public void goBack(){
        goTo(EnumPresentersNames.MAIN_PAGE_PRESENTER);
    }

    /**
     * Disconnect user
     */
    public void disconnect(){
        goTo(EnumPresentersNames.LOGIN_PAGE_PRESENTER);
    }
    @Override
    public void onEditAuthor() {
        createBookView.onEditAuthor(mainPresenter.getAuthor().toString());
    }
    @Override
    public void onDataReceived(Book data) {
        if(data!=null) {
            createBookView.setCenterBookDataPanel(new ItemBook(data.getTitle(), data.getIsbn().toString(), data.getAuthor().toString(), data.getResume()));
        }
    }
    @Override
    public void setView(IViewToPresenter basicView) {
        if(basicView instanceof IViewToCreateBookPresenter){
            createBookView=(IViewToCreateBookPresenter) basicView;
        }
    }
    @Override
    public void setViewVisible() {
        createBookView.setVisible();
    }
}




