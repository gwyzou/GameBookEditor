package org.helmo.gbeditor.infrastructure.mapping;

import org.helmo.gbeditor.infrastructure.dto.DtoAuthor;
import org.helmo.gbeditor.models.Author;

/**
 * Mapper of author
 */
public class MappingAuthor {
    /**
     * return Author object from given @param
     * @param name
     * @param surname
     * @return
     */
    public static Author getAuthor(String name, String surname){
        return new Author(name,surname);
    }

    /**
     * transform author into DtoAuthor
     * @param author
     * @return
     */
    public static DtoAuthor authorToDto(Author author){
        return new DtoAuthor(author.getName(), author.getSurname());
    }
}
