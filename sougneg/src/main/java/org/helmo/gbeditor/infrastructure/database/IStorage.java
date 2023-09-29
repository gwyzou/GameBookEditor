package org.helmo.gbeditor.infrastructure.database;

import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;

import java.util.Map;

/**
 * interface of class that will be used by BDRepository
 */
public interface IStorage extends AutoCloseable {
    /**
     * load a list of book (0 not published and1 published)
     * and map is with it's id
     * @param userId
     * @param isPublished
     * @return
     */
    Map<Book,Integer> loadBookListFromMat(int userId, int isPublished);

    /**
     * load a complete book from it's id
     * @param bookId
     * @return
     */
    Book loadFullBook(int bookId);

    /**
     * get id from @param in db
     * @param author
     * @return
     */
    int getMat(Author author);

    /**
     * add or update given book in db and return id if it's a new book -1 otherwise
     * @param idBook
     * @param from
     * @param userID
     * @return
     */
    int addOrUpdateBook(int idBook, Book from, int userID);

    /**
     * publish book whose id is @param in db
     * @param idBook
     */
    void publishBook(int idBook);

}
