package org.helmo.gbeditor.infrastructure.mapping;

import org.helmo.gbeditor.models.Choice;
import org.helmo.gbeditor.models.Page;
/**
 * Mapper of Choice
 */
public class MappingChoice {
    /**
     * return Choice object from given @param
     * @param page
     * @param text
     * @return
     */
    public static Choice getChoice(Page page, String text){
        return new Choice(page,text);
    }
}
