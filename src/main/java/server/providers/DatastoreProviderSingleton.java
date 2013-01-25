package server.providers;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.db.DatabaseBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: gh
 * Date: 1/25/13
 * Time: 11:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatastoreProviderSingleton {
    private static DatastoreProviderSingleton ourInstance = new DatastoreProviderSingleton();

    private static final String DATABASE_NAME = "mario";
    private static final String DATABASE_HOST = "127.0.0.1";
    private static final int DATABASE_PORT = 27017;

    private Morphia morphia;
    private Datastore datastore;
    private Mongo mongo;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static DatastoreProviderSingleton getInstance() {
        return ourInstance;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    private DatastoreProviderSingleton() {
        morphia = new Morphia();
        mongo = new DatabaseBuilder(DATABASE_HOST, DATABASE_PORT).build();
        datastore = morphia.createDatastore(mongo, DATABASE_NAME);

        morphia.mapPackage("dao");

        logger.info("Initialized Morphia");
    }
}
