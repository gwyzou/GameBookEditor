package org.helmo.gbeditor.infrastructure.mapping;

import org.helmo.gbeditor.models.Choice;
import org.helmo.gbeditor.models.Page;

import java.util.Collection;
/**
 * Mapper of Page
 */
public class MappingPage {
    /**
     * return Page object from given @param
     * @param text
     * @param number
     * @param choices
     * @return
     */
    public static Page getPage(String text, int number, Collection<Choice> choices){
        return new Page(text,number,choices);
    }
}
