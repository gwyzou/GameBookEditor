package org.helmo.gbeditor.views.interfaces;

import org.helmo.gbeditor.models.Page;

import java.util.Collection;

/**
 * interface extends IBasicPresenter
 * only for pagview
 */
public interface IPagePresenter extends IBasicPresenter{
    /**
     * get page list when creating newBook
     * @return
     */
    Collection<Page> getPageList();
}
