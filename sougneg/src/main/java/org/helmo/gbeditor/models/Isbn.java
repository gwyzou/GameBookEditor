package org.helmo.gbeditor.models;

/**
 * represent isbn used in a book
 */
public class Isbn {
    private transient String language;
    private transient String matricule;
    private transient String bookNumber;
    private transient int codeControl;

    /**
     * Isbn constructor
     * check if @params have wanted format else throws illegalArgumentException
     * @param isbn
     */
    public Isbn(String isbn) {
        checkPattern(isbn);
        setValues(isbn);
    }

    /**
     * Isbn constructor
     * check if @params have wanted format else throws illegalArgumentException
     * @param lang
     * @param mat
     * @param number
     */
    public Isbn(String lang,String mat,String number){
        this(lang+'-'+mat+'-'+number);
    }
    private void checkNull(String isbn){
        if(isbn==null){
            throw new IllegalArgumentException("Isbn is null");
        }
    }
    private void checkPattern(String isbn){
        checkNull(isbn);
        if (!isbn.matches("^[0-9]{1}-[0-9]{6}-[0-9]{2}-([0-9]|X){1}$") && !isbn.matches("^[0-9]{1}-[0-9]{6}-[0-9]{2}$")) {
            throw new IllegalArgumentException("Isbn Doesn't Match pattern");
        }
    }
    private void setValues(String isbn){
        this.language=String.valueOf(isbn.charAt(0));
        this.matricule=isbn.substring(2,8);
        this.bookNumber=isbn.substring(9,11);
        this.codeControl = getCodeControl();
    }
    private int getCodeControl() {

        String isbnOnNine = language+ matricule +bookNumber;
        int value=calcValueAddition(isbnOnNine);
        return moduloCode(value);

    }
    private int moduloCode(int isbnValue){
        return 11-(isbnValue % 11);

    }
    private int calcValueAddition(String isbn){
        int value = 0;
        for (int i = 10; i > 1; i--) {
            value += i * Character.getNumericValue(isbn.charAt(10 - i));
        }
        return value;
    }

    @Override
    public String toString() {
        String codeString=getCodeControlStr();
        return language + "-" + matricule + "-" + bookNumber + "-" + codeString;
    }
    private String getCodeControlStr(){
        return (codeControl==11)?"0":(codeControl==10)?"X":String.valueOf(codeControl);
    }

}
