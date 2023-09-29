package org.helmo.gbeditor.repositories;

import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;

import java.util.List;

/**
 * interface to communicate with data
 */
public interface IRepository {
    /**
     * add book to data structure
     * @param currentBook
     */
    void addBook(Book currentBook);

    /**
     * get all unpublished book from data (convert of book)
     * @return
     */
    Iterable<Book> loadAll();

    /**
     * get all published book from data (covert of book)
     * @return
     */
    Iterable<Book> getPublished();

    /**
     * look for filled book from data
     * @param isbn
     * @return
     */
    Book findBookWithIsbn(String isbn);

    /**
     * tell structure that there is a new author
     * @param author
     */
    void getMatricule(Author author);

    /**
     * publish selected book in data
     * @param isbn
     */

    void publishBook(String isbn);
}
