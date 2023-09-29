package org.helmo.gbeditor.infrastructure.database;

/**
 * interface of connection factory
 */
public interface IStorageFactory {
    /**
     *
     * @return new connection
     */
    IStorage  newStorageSession();
}
