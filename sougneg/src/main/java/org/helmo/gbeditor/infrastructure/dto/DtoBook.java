package org.helmo.gbeditor.infrastructure.dto;

/**
 * dto of book
 */
public class DtoBook {
    private String title;
    private String resume;
    private String isbn;
    private DtoAuthor author;

    /**
     * constructor of DtoBook
     * @param title
     * @param resume
     * @param isnb
     * @param author
     */
    public DtoBook(String title,String resume,String isnb,DtoAuthor author){
        this.title=title;
        this.resume=resume;
        this.isbn=isnb;
        this.author=author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public DtoAuthor getAuthor() {
        return author;
    }

    public void setAuthor(DtoAuthor author) {
        this.author = author;
    }
}
