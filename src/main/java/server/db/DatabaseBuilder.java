package server.db;

import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

/**
 *
 */
public class DatabaseBuilder {
    private final String hostname;
    private final int port;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public DatabaseBuilder(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public Mongo build() {
        try {
            return new Mongo(hostname, port);
        } catch (UnknownHostException e) {
            logger.error("Could not build() the connection", e);
            return null;
        }
    }
}
