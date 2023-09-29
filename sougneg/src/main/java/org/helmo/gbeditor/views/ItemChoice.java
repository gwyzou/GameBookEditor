package org.helmo.gbeditor.views;

/**
 * item view of choice
 */
public class ItemChoice {
    private final ItemPage pageChoice;
    private final String textChoice;

    /**
     * constructor
     * @param text
     * @param itemPage
     */
    public ItemChoice(String text,ItemPage itemPage){
        this.textChoice =text;
        this.pageChoice =itemPage;
    }

    /**
     * getter item Page linked to
     * @return
     */
    public ItemPage getPageChoice(){
        return this.pageChoice;
    }

    /**
     * getter text choice
     * @return
     */
    public String getTextChoice(){
        return this.textChoice;
    }
}
