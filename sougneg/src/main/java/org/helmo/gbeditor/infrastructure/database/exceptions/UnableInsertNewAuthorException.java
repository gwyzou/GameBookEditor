package org.helmo.gbeditor.infrastructure.database.exceptions;

import java.sql.SQLException;

/**
 * Couldn't create new author in DB
 */
public class UnableInsertNewAuthorException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Couldn't create new author in DB
     * @param ex exception
     */
    public UnableInsertNewAuthorException(SQLException ex){
        super(ex);
    }
}
