package org.helmo.gbeditor.infrastructure.database.exceptions;

/**
 * Couldn't teardown Database
 */
public class UnableTearDownException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Couldn't teardown Database
     * @param e
     */
    public UnableTearDownException(Exception e) {super(e);}
}
