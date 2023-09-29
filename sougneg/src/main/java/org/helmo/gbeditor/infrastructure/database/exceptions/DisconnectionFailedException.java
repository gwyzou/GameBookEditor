package org.helmo.gbeditor.infrastructure.database.exceptions;

import java.sql.SQLException;

/**
 * couldn't disconnect from db
 */
public class DisconnectionFailedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Couldn't disconnect from db, extends RuntimeException
     * @param ex thrown exception
     */
    public DisconnectionFailedException(SQLException ex) {
        super(ex);
    }
}

