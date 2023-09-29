package org.helmo.gbeditor.presenters.interfaces;

import org.helmo.gbeditor.views.ItemBook;

/**
* sub IVewToPresenter
 */
public interface IViewToCreateBookPresenter extends IViewToPresenter {
    /**
     * set data to view
     * @param itemBook
     */
    void setCenterBookDataPanel(ItemBook itemBook);
}
