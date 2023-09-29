package org.helmo.gbeditor.infrastructure.database;

import org.helmo.gbeditor.infrastructure.Repository;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;

import java.util.*;

/**
 * Class implements IRepository
 * store data such as id from database and is a link between presenter and SqlGameBook
 */
public class BDRepository extends Repository {
    private transient final IStorageFactory factory;
    private transient int userID;

    /**
     * constructor of BDRepository
     * @param factory
     */
    public BDRepository(IStorageFactory factory){
        this.factory =factory;
    }

    @Override
    public void addBook(Book currentBook) {
        try(var connection = factory.newStorageSession()) {
             int index=connection.addOrUpdateBook(getBookID(currentBook),currentBook,userID);
             if(index>= ID_MIN_VALUE){
                addToMap(currentBook,index);
             }
             currentBook.clearPageList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Book> loadAll() {
            return new ArrayList<>(bookMap.keySet());
    }

    @Override
    public List<Book> getPublished() {
        return new ArrayList<>(published);
    }

    private void setFirstLoadAll(){
        bookMap.clear();
        published.clear();
        try(var connection = factory.newStorageSession()) {
            Map<Book,Integer> newMap =new HashMap<>(connection.loadBookListFromMat(userID,0));

            published.addAll(connection.loadBookListFromMat(userID,1).keySet());
            bookMap.putAll(newMap);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findBookWithIsbn(String isbn) {
        int idBook = getBookID(getBookFromMap(isbn));
        if(idBook< ID_MIN_VALUE){
            throw new RuntimeException();
        }
        return loadFullBook(idBook,isbn);
    }
    private Book loadFullBook(int idBook,String isbn){
        try(var connection = factory.newStorageSession()) {
            bookMap.remove(getBookFromMap(isbn));
            Book toAdd= connection.loadFullBook(idBook);
            bookMap.put(toAdd,idBook);

            return toAdd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void getMatricule(Author author) {
        try(var connection = factory.newStorageSession()) {
            userID=connection.getMat(author);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setFirstLoadAll();
    }

    @Override
    public void publishBook(String isbn) {
        try(var connection = factory.newStorageSession()) {
            Book bookToPublish =getBookFromMap(isbn);
            connection.publishBook(getBookID(bookToPublish));
            bookMap.remove(bookToPublish);
            published.add(bookToPublish);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
