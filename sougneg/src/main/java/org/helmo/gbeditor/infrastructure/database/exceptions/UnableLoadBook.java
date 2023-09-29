package org.helmo.gbeditor.infrastructure.database.exceptions;

import java.sql.SQLException;

/**
 * Couldn't load wanted data from DB
 */
public class UnableLoadBook extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Couldn't load wanted data from DB
     * @param e
     */
    public UnableLoadBook(SQLException e) {
        super(e);
    }
}
