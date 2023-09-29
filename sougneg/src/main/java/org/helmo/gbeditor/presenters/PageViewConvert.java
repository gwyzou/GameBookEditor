package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.models.Page;
import org.helmo.gbeditor.views.ItemChoice;
import org.helmo.gbeditor.views.ItemPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * convert collection of page to List of itemPage
 */
public class PageViewConvert {
    /**
     * converter
     * @param page
     * @return
     */
    public static List<ItemPage> PageToViewConvert(Collection<Page> page){
        List<Page> pageList= new ArrayList<>();
        if(page!=null){
            pageList.addAll(page);
        }
        List<ItemPage> itemPages=new ArrayList<>();
        pageList.forEach(currentPage->itemPages.add(new ItemPage(currentPage.getText(),currentPage.getPageNbr())));
        linkChoices(pageList,itemPages);
        return  itemPages;
    }
    private static void linkChoices(List<Page> pageList, List<ItemPage> itemPages){
        for (Page page: pageList) {
            ItemPage pageImage = itemPages.get(page.getPageNbr()-1);
            page.getChoices().forEach(choice->{
                pageImage.addChoice(new ItemChoice(choice.getTextChoice(), itemPages.get(pageList.indexOf(choice.getPageChoice()))));
            });
        }
    }
}
