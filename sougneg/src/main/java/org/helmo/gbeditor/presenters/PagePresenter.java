package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Choice;
import org.helmo.gbeditor.models.Isbn;
import org.helmo.gbeditor.models.Page;
import org.helmo.gbeditor.views.interfaces.IPagePresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewToPagePresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewToPresenter;
import org.helmo.gbeditor.views.ItemChoice;
import org.helmo.gbeditor.views.ItemPage;

import java.util.Collection;


/**
 * presenter of sub view viewPage
 */
public class PagePresenter implements IPagePresenter {
    private transient IViewToPagePresenter pageView ;

    private transient Book book;
    private transient final MainPresenter mainPresenter;

    /**
     * subscribe itself to the mainPresenter
     * @param mainPresenter
     */
    public PagePresenter(MainPresenter mainPresenter){
        this.mainPresenter=mainPresenter;
        mainPresenter.addPresenter(EnumPresentersNames.PAGE_PRESENTER,this);
    }
    private void setListToView(int autofocus){
        if(book!=null){
            pageView.setList(PageViewConvert.PageToViewConvert(book.getPageList()),autofocus);
        }

    }
    @Override
    public void onDataReceived(Book book) {
        if(book!=null){
            this.book=book;
        }else{
            this.book=new Book("t","t",new Isbn("0-000000-00-0"),mainPresenter.getAuthor());
        }
        setListToView(-1);
    }

    @Override
    public void setView(IViewToPresenter basicView) {
        if(basicView instanceof IViewToPagePresenter){
            this.pageView=(IViewToPagePresenter) basicView;
        }else{
            return;
        }
    }


    /**
     * check input else send error message
     * @param pageText
     * @param pageNbr
     * @return
     */
    public boolean newInputIsCorrectValue(String pageText,String pageNbr){
        if(newInputIsCorrectValue(pageText) && isInt(pageNbr)){
            return true;
        }
        pageView.showError("Number is needed");
        return false;
    }

    /**
     * check input else send error message
     * @param pageText
     * @return
     */
    public boolean newInputIsCorrectValue(String pageText){
        if(!isNotBlank(pageText)){
            pageView.showError("Text has to be filled");
            return false;
        }
        return true;
    }

    /**
     * add new page to book
     * @param text
     * @param nbr
     */
    public void addCorrectedNewPage(String text, String nbr){
        String newNbr = nbr.trim();
        try {
            if (!isInt(newNbr)) {
                book.addNewPage(text, -1);
                setListToView(-1);
            } else {
                book.addNewPage(text, Integer.parseInt(newNbr));
                setListToView(-1);
            }
        }catch (IllegalArgumentException e){
            pageView.showError(e.getMessage());
        }

    }

    /**
     * check input
     * @param pageText
     * @param pageNbr
     * @param pageFrom
     * @return
     */
    public boolean newInputChoice(String pageText, String pageNbr, int pageFrom){
        if(newInputIsCorrectValue(pageText,pageNbr)){
            int to =Integer.parseInt(pageNbr);
            if(!inRangeOfOList(to-1,book.getPageList().size())){
                pageView.showError("page specified does not exist");
            }
            return checkPageNbrEquals(to,pageFrom);
        }
        return false;
    }

    /**
     * add new choice
     * @param text
     * @param pageNbr
     * @param addTo
     */
    public void addCorrectedNewChoice(String text, int pageNbr, ItemPage addTo){
        try{
            Choice choice = new Choice(book.getPageList().get(pageNbr-1),text);
            book.getPageList().get(addTo.getPageNumber()-1).addLink(choice);
            setListToView(addTo.getPageNumber()-1);
        }catch (IllegalArgumentException e){
            pageView.showError("wrong input");
        }
    }
    private boolean checkPageNbrEquals(int to, int pageFrom){
        if(to==pageFrom){
            pageView.showError("cannot link to page to itself");
            return false;
        }else{
            return true;
        }
    }

    /**
     * remove selected choice from page
     * @param itemPage
     * @param choice
     */
    public void deleteChoice(ItemPage itemPage, ItemChoice choice) {
        Page toEdit=book.getPageList().get(itemPage.getPageNumber()-1);
        toEdit.removeLink(book.getPageList().get(choice.getPageChoice().getPageNumber()-1));
        setListToView(toEdit.getPageNbr()-1);
    }

    /**
     * check if input in range
     * @param input
     * @return true if in range else false
     */
    public boolean inRangeDeletePage(String input){
        if(inRange(input, book.getPageList().size())){
            return true;
        }
        pageView.showError("input page doesn't exist");
        return false;
    }

    /**
     * remove page from list
     * @param pageNbr
     */
    public void deletePage(int pageNbr){
        book.deletePage(pageNbr-1);
        setListToView(-1);
    }
    private boolean isInt(String str){
        return isNotBlank(str) && str.trim().matches("^[0-9]*$");
    }
    private boolean isNotBlank(String str){
        return isNotNull(str) && !str.isBlank();
    }
    private boolean isNotNull(String str){
        return str!=null;
    }
    private boolean inRange(String input,int maxLength){
        return isInt(input) && inRangeOfOList(Integer.parseInt(input) - 1, maxLength);
    }

    private boolean inRangeOfOList(int index,int maxIndex){
        return index>=0 && index<maxIndex;
    }

    @Override
    public void onEditAuthor() {
    }

    @Override
    public void setViewVisible() {
    }

    @Override
    public Collection<Page> getPageList() {
        return this.book.getPageList();
    }

    /**
     * get number of choices linked to selected page
     * @param pageNumber
     * @return
     */
    public int calculLinkToPage(int pageNumber){
        return book.numberLinkOfPage(pageNumber-1);
    }
}
