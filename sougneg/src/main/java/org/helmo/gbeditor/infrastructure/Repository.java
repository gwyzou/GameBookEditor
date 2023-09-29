package org.helmo.gbeditor.infrastructure;

import org.helmo.gbeditor.repositories.IRepository;
import org.helmo.gbeditor.models.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class which will be extended to all future repositories
 */
public abstract class Repository implements IRepository {
    /**
     * mapping of book model and id in database
     */
    protected transient final Map<Book,Integer> bookMap = new HashMap<>();
    /**
     * list of published books
     */
    protected final List<Book> published = new ArrayList<>();
    /**
     * minimum value in bookmap
     */
    protected static  final int ID_MIN_VALUE =1;

    /**
     * return id of book or -1 if not found
     * @param book
     * @return
     */
    protected int getBookID(Book book){
        if(bookMap.get(book)!=null){
            return bookMap.get(book);
        }
        return -1;
    }

    /**
     * add book to map with id value
     * @param book
     * @param id
     */
    protected void addToMap(Book book,int id){
        if(id>= ID_MIN_VALUE){
            bookMap.put(book,id);
        }
    }

    /**
     * return book whose isbn is @param
     * @param isbn
     * @return
     */
    protected Book getBookFromMap(String isbn){
        return bookMap.keySet().stream()
                .filter(book -> book.getIsbn().toString().equals(isbn))
                .findFirst().orElse(null);
    }
}
