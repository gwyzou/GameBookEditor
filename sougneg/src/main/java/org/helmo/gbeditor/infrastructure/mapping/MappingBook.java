package org.helmo.gbeditor.infrastructure.mapping;

import org.helmo.gbeditor.infrastructure.dto.DtoBook;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Isbn;
/**
 * Mapper of Book
 */
public class MappingBook {
    /**
     * return Book object from given @param
     * @param title
     * @param resume
     * @param isbn
     * @param author
     * @return
     */
    public static Book getBook(String title, String resume, Isbn isbn, Author author){
        return new Book(title,resume,isbn,author);
    }

    /**
     * transform Book into DtoBook
     * @param book
     * @return
     */
    public static DtoBook bookToDto(Book book){
        return new DtoBook(book.getTitle(),book.getResume(),book.getIsbn().toString(),MappingAuthor.authorToDto(book.getAuthor()) );
    }
}
