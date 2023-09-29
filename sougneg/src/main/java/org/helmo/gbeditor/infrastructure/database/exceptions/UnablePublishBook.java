package org.helmo.gbeditor.infrastructure.database.exceptions;

import java.sql.SQLException;

/**
 * Couldn't publish wanted book
 */
public class UnablePublishBook extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     *  * Couldn't publish wanted book
     * @param e
     */
    public UnablePublishBook(SQLException e){
        super(e);
    }
}
