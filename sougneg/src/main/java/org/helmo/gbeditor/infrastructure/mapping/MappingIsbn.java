package org.helmo.gbeditor.infrastructure.mapping;

import org.helmo.gbeditor.models.Isbn;
/**
 * Mapper of Isbn
 */
public class MappingIsbn {
    /**
     * return Isbn object from given @param
     * @param input
     * @return
     */
    public static Isbn getISbn(String input){
        return new Isbn(input);
    }
}
