package org.helmo.gbeditor.models;

/**
 * Choice is a class which link two page with a text
 * page it is placed in to page attribute
 */
public class Choice {
    private transient final Page page;
    private transient final String text;

    /**
     * check values and throws illegal argument exception when doesn't meet requirement
     * @param page
     * @param text
     */
    public Choice(Page page,String text){
        tryBlank(text);
        tryNull(page);
        this.page=page;
        this.text=text;
    }
    private void tryNull(String input){
        if(input== null){
            throw new IllegalArgumentException("Text is null");
        }
    }
    private void tryBlank(String input){
        tryNull(input);
        if(input.isBlank()){
            throw new IllegalArgumentException("Text is empty");
        }
    }
    private void tryNull(Page page){
        if(page==null){
            throw new IllegalArgumentException("Page is null");
        }
    }

    /**
     * getter of selected page
     * @return Page
     */
    public Page getPageChoice(){
        return this.page;
    }

    /**
     * getter of text assigned to the choice
     * @return String
     */
    public String getTextChoice(){
        return this.text;
    }
}
