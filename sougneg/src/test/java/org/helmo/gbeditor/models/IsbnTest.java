package org.helmo.gbeditor.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsbnTest {
    @Test
    public void constructorOnNull(){

        Exception e=assertThrows(IllegalArgumentException.class, () -> new Isbn(null));
        assertEquals("Isbn is null",e.getMessage());
    }
    @Test
    public void constructorOnDifferentPattern(){
        Exception e=assertThrows(IllegalArgumentException.class, () -> new Isbn("a"));
        assertEquals("Isbn Doesn't Match pattern",e.getMessage());
    }
    @Test
    public void noExceptionOnCorrectISBN(){
        assertAll(() -> new Isbn("1-234567-89"));
    }
    @Test
    public void checkCodeControl(){
        assertEquals("1-234567-89-X", new Isbn("1-234567-89").toString());
        assertEquals("1-234567-88-1", new Isbn("1-234567-88").toString());
        assertEquals("1-234567-83-0", new Isbn("1-234567-83").toString());
        assertEquals("0-000000-00-0", new Isbn("0-000000-00").toString());


    }
}
