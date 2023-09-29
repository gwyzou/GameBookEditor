package org.helmo.gbeditor.infrastructure.database.exceptions;

/**
 * Error during setup or transaction
 */
public class UnableSetupException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Error during setup or transaction
     * @param ex
     */
    public UnableSetupException(Exception ex) {
        super(ex);
    }
}
