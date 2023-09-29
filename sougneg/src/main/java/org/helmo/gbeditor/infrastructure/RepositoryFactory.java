package org.helmo.gbeditor.infrastructure;

import org.helmo.gbeditor.repositories.IRepository;
import org.helmo.gbeditor.infrastructure.database.BDRepository;
import org.helmo.gbeditor.infrastructure.database.sql.SqlStorageFactory;

/**
 * factory used to create wanted repository
 */
public class RepositoryFactory {
    /**
     * return wanted repository
     * @param type
     * @return
     */
    public static IRepository getRepo(RepoType type){
        if (type == RepoType.MYSQL) {
            return new BDRepository(new SqlStorageFactory(
                    //        "com.mysql.cj.jdbc.Driver",
                    //        "jdbc:mysql://192.168.128.13:3306/in21b10310",
                    //        "in21b10310",
                    //        "0310"
                    "com.mysql.cj.jdbc.Driver", "jdbc:mysql://asterix-intra.cg.helmo.be:13306/I210310", "I210310", "0310"
            ));
        }
        return null;
    }
}
