package org.helmo.gbeditor.infrastructure.database.exceptions;

import java.sql.SQLException;

/**
 * Couldn't get wanted data from Author Table
 */
public class UnableGetAuthorDataException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Couldn't get wanted data from author table
     * @param ex exception
     */
    public UnableGetAuthorDataException(SQLException ex){
        super(ex);
    }
}
