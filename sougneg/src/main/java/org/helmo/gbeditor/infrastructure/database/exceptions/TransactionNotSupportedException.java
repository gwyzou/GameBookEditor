package org.helmo.gbeditor.infrastructure.database.exceptions;

import java.sql.SQLException;

/**
 * Transaction is not supported by used DB
 */
public class TransactionNotSupportedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Transaction is not supported by used DB , extends RuntimeException
     * @param ex
     */
    public TransactionNotSupportedException(SQLException ex) {
        super(ex);
    }
}
