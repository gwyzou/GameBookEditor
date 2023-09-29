package org.helmo.gbeditor.infrastructure.database.exceptions;

/**
 * Couldn't load Driver
 */
public class SqlDriverNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Couldn't load Driver exception , extends RuntimeException
     * @param driverName driver
     */
    public SqlDriverNotFoundException(String driverName) {
        super("Unable to load driver "+driverName);
    }
}
