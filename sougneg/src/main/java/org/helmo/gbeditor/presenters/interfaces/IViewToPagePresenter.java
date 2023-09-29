package org.helmo.gbeditor.presenters.interfaces;

import org.helmo.gbeditor.views.ItemPage;

import java.util.Collection;

/**
 * sub IVewToPresenter to assign data
 */
public interface IViewToPagePresenter extends IViewToPresenter{
     /**
      * tell view to actualise itself with sent data
      * @param pageList
      * @param autoFocus
      */
     void setList(Collection<ItemPage> pageList, int autoFocus);
}
