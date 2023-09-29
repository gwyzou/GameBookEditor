package org.helmo.gbeditor.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {
    @Test
    public void constructorOnNull(){
        Exception e=assertThrows(IllegalArgumentException.class, () -> new Author(null,"a"));
        assertEquals("Both Name and Surname are required",e.getMessage());

        e=assertThrows(IllegalArgumentException.class, () -> new Author("a",null));
        assertEquals("Both Name and Surname are required",e.getMessage());
    }
    @Test
    public void constructorOnBlank(){
        Exception e=assertThrows(IllegalArgumentException.class, () -> new Author("","a"));
        assertEquals("Both Name and Surname are required",e.getMessage());

        e=assertThrows(IllegalArgumentException.class, () -> new Author("a",""));
        assertEquals("Both Name and Surname are required",e.getMessage());
    }
    @Test
    public void constructorWithNoThrow(){
        assertAll(() -> new Author("Moi","Moi.2"));
    }
    @Test
    public void getters(){
       Author test= new Author("Moi","Moi.2");
       assertEquals("Moi",test.getName());
       assertEquals("Moi.2",test.getSurname());
       assertEquals("Moi Moi.2",test.toString());
    }
}
