package org.helmo.gbeditor.infrastructure.database.exceptions;

/**
 * Exception when can't connect
 */
public class ConnectionFailedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * exception when can't connect  , extends RuntimeException
     * @param s message
     * @param ex stackTrace
     */
    public ConnectionFailedException(String s, Exception ex) {
        super(s, ex);
    }
}
