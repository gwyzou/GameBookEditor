package org.helmo.gbeditor.infrastructure.database.sql;

import org.helmo.gbeditor.infrastructure.database.IStorageFactory;
import org.helmo.gbeditor.infrastructure.database.exceptions.ConnectionFailedException;
import org.helmo.gbeditor.infrastructure.database.exceptions.SqlDriverNotFoundException;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class used to create a new Connection to the specified Data base
 */
public class SqlStorageFactory implements IStorageFactory {
    private transient final String db;//url
    private transient final String username;
    private transient final String password;

    /**
     * create a new Link to @param data base
     * @param driverName driver used
     * @param db url
     * @param username to connect
     * @param pass password
     */
    public SqlStorageFactory(String driverName, String db, String username, String pass) {
        try {
            Class.forName(driverName);
            this.db=db;
            this.password=pass;
            this.username=username;

        } catch (ClassNotFoundException e) {

            throw new SqlDriverNotFoundException(driverName);

        }
    }

    /**
     * create a new session to use
     * @return SqlGameBook
     */
    @Override
    public SqlStorage newStorageSession() {
        try {
            return new SqlStorage(DriverManager.getConnection(db,username,password));
        } catch (SQLException e) {
            throw new ConnectionFailedException("Unable to access db"+db,e);
        }
    }
}
