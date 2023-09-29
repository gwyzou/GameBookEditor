package org.helmo.gbeditor.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    public void constructorOnNull(){

        Exception e=assertThrows(IllegalArgumentException.class, () -> new Book(null,"t",new Isbn("1-234567-89"),new Author("a","a")));
        assertEquals("Unauthorised null value",e.getMessage());

        e=assertThrows(IllegalArgumentException.class, () -> new Book("null",null,new Isbn("1-234567-89"),new Author("a","a")));
        assertEquals("Unauthorised null value",e.getMessage());

        e=assertThrows(IllegalArgumentException.class, () -> new Book("null","t",null,new Author("a","a")));
        assertEquals("Unauthorised null value",e.getMessage());

        e=assertThrows(IllegalArgumentException.class, () -> new Book("null","t",new Isbn("1-234567-89"),null));
        assertEquals("Unauthorised null value",e.getMessage());
    }
    @Test
    public void constructOnBlank(){
        Exception e=assertThrows(IllegalArgumentException.class, () -> new Book("","t",new Isbn("1-234567-89"),new Author("a","a")));
        assertEquals("String cannot be Blank",e.getMessage());

        e=assertThrows(IllegalArgumentException.class, () -> new Book("t","",new Isbn("1-234567-89"),new Author("a","a")));
        assertEquals("String cannot be Blank",e.getMessage());
    }
    @Test
    public void constructOnMaxLength(){

        String size150="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String size500="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertEquals(150,size150.length());
        assertEquals(500,size500.length());

        Exception e=assertThrows(IllegalArgumentException.class, () -> new Book(size150+"a",size500,new Isbn("1-234567-89"),new Author("a","a")));
        assertEquals("String Length value exceeded max authorised Value",e.getMessage());

        e=assertThrows(IllegalArgumentException.class, () -> new Book(size150,size500+"a",new Isbn("1-234567-89"),new Author("a","a")));
        assertEquals("String Length value exceeded max authorised Value",e.getMessage());

    }
    @Test
    public void constructorWithNoThrow(){
        assertAll(() -> new Book("Super Livre","C'est le meilleur livre du monde",
                new Isbn("1-234567-89"),new Author("Moi","Moi.2")));
    }
    @Test
    public void getters(){
         Book test=new Book("Super Livre","C'est le meilleur livre du monde",
                new Isbn("1-234567-89"),new Author("Moi","Moi.2"));

         assertEquals("Super Livre",test.getTitle());
         assertEquals("C'est le meilleur livre du monde",test.getResume());
         assertEquals("1-234567-89-X",test.getIsbn().toString());
         assertEquals("Moi",test.getAuthor().getName());
         assertEquals("Moi.2",test.getAuthor().getSurname());

    }
}
