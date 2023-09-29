package org.helmo.gbeditor.views;

import java.util.ArrayList;
import java.util.List;

/**
 * item view of page
 */
public class ItemPage {
    private final String text;
    private final int pageNumber;
    private transient final List<ItemChoice> itemChoiceList=new ArrayList<>();

    /**
     * constructor
     * @param text
     * @param pageNumber
     */
    public ItemPage(String text,int pageNumber){
        this.text= text;
        this.pageNumber=pageNumber;
    }

    /**
     * add item choice to item page
     * @param itemChoice
     */
    public void addChoice(ItemChoice itemChoice){
        if(itemChoice!=null){
            this.itemChoiceList.add(itemChoice);
        }
    }

    /**
     * getter of text page
     * @return
     */
    public String getText(){
        return this.text;
    }

    /**
     * getter of page number
     * @return
     */
    public int getPageNumber(){
        return pageNumber;
    }

    /**
     * iterable on choices
     * @return
     */
    public Iterable<ItemChoice> getChoices(){
        return  itemChoiceList;
    }
    @Override
    public String toString(){
        return "Page : "+this.pageNumber;
    }
}
