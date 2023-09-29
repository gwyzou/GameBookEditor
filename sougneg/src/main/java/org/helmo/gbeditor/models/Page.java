package org.helmo.gbeditor.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * represent poge model
 */
public class Page implements Comparable<Page>{
    private static final int MIN_PAGE = 1;
    private final String text ;
    private  transient int pageNbr;
    private transient final List<Choice> choice=new ArrayList<>() ;

    /**
     * construcor of page throws exception if @param are incorrect
     * @param text
     * @param numPage
     * @param collection
     */
    public Page(String text, int numPage, Collection<Choice> collection){
        checkPageNbr(numPage);
        this.pageNbr =numPage;
        this.text=String.valueOf(text);
        if(collection!=null){
            this.choice.addAll(collection);
        }
    }
    private void checkPageNbr(int numPage){
        if(numPage<MIN_PAGE){
            throw new IllegalArgumentException("Page number must be higher than 0");
        }
    }

    /**
     * return text of page
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * iterator on each choice assigned from the page
     * @return
     */
    public Iterable<Choice> getChoices(){
        return choice;
    }

    /**
     * add new choice starting from the page
     * @param choice
     */

    public void addLink(Choice choice){
        if(choice.getPageChoice()==this){
            throw new IllegalArgumentException("Cannot link the page to itself");
        }
            this.choice.add(choice);
    }

    /**
     * remove a choice
     * @param page
     */
    public void removeLink(Page page){
        List<Choice> listToRemove=new ArrayList<>();
        choice.forEach(choice->{
            if(choice.getPageChoice() == page){
                listToRemove.add(choice);
            }
        });
        choice.removeAll(listToRemove);
    }

    /**
     * get page number
     * @return
     */
    public int getPageNbr(){
        return this.pageNbr;
    }

    /**
     * add 1 to page number
     */
    public void pageAddOne(){
        pageNbr++;
    }

    /**
     * minus 1 to page number
     */
    public void pageMinusOne(){
        pageNbr--;
    }

    @Override
    public int compareTo(Page o) {
        return Integer.compare(this.pageNbr, o.getPageNbr());
    }
    @Override
    public String toString(){
        return "Page : "+this.pageNbr;
    }
}
