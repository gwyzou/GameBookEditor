package org.helmo.gbeditor.presenters.interfaces;

/**
 * interface given to presenter
 */
public interface IViewToPresenter {
    /**
     * tell view there is an error to display
     * @param e
     */
    void showError(String e);

    /**
     * tell view to change author name
     * @param authorName
     */
    void onEditAuthor(String authorName);

    /**
     * tell view to ask ViewStack to display her
     */
    void setVisible();

}
