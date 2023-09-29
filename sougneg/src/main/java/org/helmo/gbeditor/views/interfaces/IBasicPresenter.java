package org.helmo.gbeditor.views.interfaces;


import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.presenters.interfaces.IViewToPresenter;

/**
 * method needed to a presenter in order to work well
 */
public interface IBasicPresenter {
    /**
     * when author is edited send signal to other
     */
    void onEditAuthor();

    /**
     * tell presenter to tell it's view to display herself
     */
    void setViewVisible();

    /**
     * do somthing when data is received
     * @param book
     */
    void onDataReceived(Book book);

    /**
     * assign view to the presenter
     * @param basicView
     */
    void setView(IViewToPresenter basicView);

}
