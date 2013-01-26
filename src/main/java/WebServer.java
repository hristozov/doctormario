import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import org.apache.log4j.Logger;
import server.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class WebServer implements Server {
    private Logger logger = Logger.getLogger(getClass());

    public static void main(String[] args) {
        new WebServer().start();
    }

    public void start() {

        final String baseUri = "http://0.0.0.0:8080/";
        final Map<String, String> initParams = new HashMap<String, String>();

        initParams.put("com.sun.jersey.config.property.packages","service");
        initParams.put("com.sun.jersey.api.json.POJOMappingFeature","true");

        logger.info("Starting Grizzly...");
        try {
            SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
            logger.info("Grizzly started.");
        } catch (IOException e) {
            logger.error("We're fucked.", e);
        }
    }


}
