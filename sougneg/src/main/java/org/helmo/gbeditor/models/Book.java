package org.helmo.gbeditor.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * represent book model
 */
public class Book {
    private transient String title;
    private transient String resume;
    private transient Isbn isbn;
    private final Author author;

    private final List<Page> pageList=new ArrayList<>();

    /**
     * Book constructor
     * throws exception if @param unimplemented
     * and for strings if empty or too big
     * @param title maxlength 150
     * @param resume maxlength 500
     * @param isbn
     * @param author
     */
    public Book(String title, String resume, Isbn isbn, Author author){
        testLength(title,150);
        testLength(resume,500);
        testNull(isbn);
        testNull(author);
        this.title= title;
        this.resume= resume;
        this.isbn= isbn;
        this.author= author;
    }


    private void testNull(Object toTest){
        if (toTest==null) {
            throw new IllegalArgumentException("Unauthorised null value");
        }
    }
    private void testBlank(String toTest){
        testNull(toTest);
        if(toTest.isBlank()) {
            throw new IllegalArgumentException("String cannot be Blank");
        }
    }
    private void testLength(String toTest,int maxValue){
        testBlank(toTest);
        if(toTest.length()>maxValue) {
            throw new IllegalArgumentException("String Length value exceeded max authorised Value");
        }
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Author getAuthor() {
        return author;
    }
    public List<Page> getPageList() {
        Collections.sort(pageList);
        return  pageList;
    }
    public void setPageList(Collection<Page> pages){
        if(pages!=null){
            pageList.addAll(pages);
        }
    }

    /**
     * add new page to list
     * @param text
     * @param pageNbr
     */
    public void addNewPage(String text,int pageNbr){
        if(!inRange(pageNbr)){
            addLastPage(text);
        }else{
            addPageAt(text,pageNbr);
        }
    }
    private void addPageAt(String text,int pageNbr){
        pageList.forEach(page->{
            if(page.getPageNbr()>=pageNbr){
                page.pageAddOne();
            }
        });
        Page page =new Page(text,pageNbr,null);
        pageList.add(pageNbr-1,page);
    }
    private boolean inRange(int pageNbr){
        return pageNbr>=0 && pageNbr<pageList.size();
    }

    /**
     * remove page at selection index
     * @param index
     */
    public void deletePage(int index){
        Page pageDel = pageList.get(index);
        pageList.forEach(page->{
            if(page.getPageNbr()>index){
                page.pageMinusOne();
            }
            page.removeLink(pageDel);
        });
        pageList.remove(pageDel);
    }

    private void addLastPage(String text){
        pageList.add(new Page(text,pageList.size()+1,null));
    }

    /**
     * edit primary data
     * @param title
     * @param resume
     * @param isbn
     */
    public void setPrimaryData(String title, String resume,Isbn isbn){
        testLength(title,150);
        testLength(resume,500);
        testNull(isbn);
        this.title=title;
        this.resume=resume;
        this.isbn=isbn;

    }

    /**
     * clear page data
     */
    public void clearPageList(){
        pageList.clear();
    }

    /**
     * count number of pages linked to selected
     * @param index
     * @return
     */
    public int numberLinkOfPage(int index) {
        int toReturn=0;
        for(Page page : pageList){
            for(Choice choice:page.getChoices()){
                if(choice.getPageChoice()== pageList.get(index)){
                    toReturn++;
                }
            }
        }
        return toReturn;
    }
}
