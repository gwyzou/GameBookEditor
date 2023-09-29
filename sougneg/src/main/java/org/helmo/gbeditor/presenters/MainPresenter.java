package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Page;
import org.helmo.gbeditor.views.interfaces.IBasicPresenter;
import org.helmo.gbeditor.views.interfaces.IPagePresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class which let presenter dialog between themselves
 */
public class MainPresenter {
    private transient final Map<EnumPresentersNames, IBasicPresenter> presenterMap = new TreeMap<>();

    private final Author author;
    private transient Book initialBook;

    /**
     * constructor , initialise author
     */
    public MainPresenter(){
        author=new Author("Doe","John");
    }

    /**
     * tell presenter associated with selected @param to display it's view
     * @param name
     */
    public void goTo(EnumPresentersNames name){
        presenterMap.get(name).setViewVisible();
    }

    /**
     * add a new association to map
     * @param name
     * @param basicPresenter
     */
    public void addPresenter(EnumPresentersNames name, IBasicPresenter basicPresenter){
        presenterMap.put(name,basicPresenter);
    }

    /**
     * getter of author
     * @return
     */
    public Author getAuthor(){
        return this.author;
    }

    /**
     * getter of initial book
     * @return
     */
    public Book getInitialBook(){
        return initialBook;
    }

    /**
     * set new attributes to author
     * @param author
     */
    public void setAuthor(Author author){
        this.author.setNewAuthor(author.getName(), author.getSurname());
        presenterMap.values().forEach(IBasicPresenter::onEditAuthor);
    }

    /**
     * when called send notification to all presenters of the map
     * @param book
     */
    public void onEditBook(Book book) {
        goTo(EnumPresentersNames.CREATE_BOOK_PRESENTER);
        this.initialBook = book;
        presenterMap.get(EnumPresentersNames.CREATE_BOOK_PRESENTER).onDataReceived(book);
        presenterMap.get(EnumPresentersNames.PAGE_PRESENTER).onDataReceived(book);
    }

    /**
     * get page list when newbook
     * @return
     */
    public Collection<Page> getPageList() {
        if(presenterMap.get(EnumPresentersNames.PAGE_PRESENTER) instanceof IPagePresenter){
            return ((IPagePresenter) presenterMap.get(EnumPresentersNames.PAGE_PRESENTER)).getPageList();
        }else{
            return new ArrayList<>();
        }
    }
}
