package org.helmo.gbeditor.infrastructure.database.exceptions;

import java.sql.SQLException;

/**
 * Couldn't roll back transaction
 */
public class UnableToRollBackException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Couldn't roll back transaction
     * @param ex
     */
    public UnableToRollBackException(SQLException ex){
        super(ex);
    }
}
