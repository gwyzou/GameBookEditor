package org.helmo.gbeditor.views;

/**
 * view model of book
 */
public class ItemBook {
    private final String title;
    private final String isbn;
    private final String author;
    private final String resume;

    /**
     * constructor
     * @param title
     * @param isbn
     * @param author
     * @param resume
     */
    public ItemBook(String title, String isbn, String author, String resume){
        this.title= title.trim();
        this.author= author.trim();
        this.resume= resume.trim();
        this.isbn=isbn.trim();
    }
    @Override
    public String toString(){
        return String.format("Titre: %.15s\nIsbn: %s\nAuthor: %s",title,isbn,author);
    }

    /**
     * getter title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter author
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * getter Resume
     * @return
     */
    public String getResume() {
        return resume;
    }

    /**
     * getter Language
     * @return
     */
    public String getLanguage() {
        return isbn.substring(0,1);
    }

    /**
     * getter bookNbr
     * @return
     */
    public String getBookNumber() {
        return isbn.substring(9,11);
    }

    /**
     * getter isbn
     * @return
     */
    public String getIsbn() {
        return isbn;
    }
}
