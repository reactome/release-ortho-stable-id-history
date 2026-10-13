package org.reactome.release.orthoStableIdentifierHistory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gk.persistence.MySQLAdaptor;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final String RESOURCES_DIR = Paths.get("src", "main", "resources").toString();

    public static void main(String[] args) throws Exception {

        String pathToConfig = args.length > 0 ? args[0] : Paths.get(RESOURCES_DIR ,"config.properties").toString();

        Properties props = new Properties();
        props.load(new FileInputStream(pathToConfig));

        //Set up DB adaptors
        String releaseUsername = props.getProperty("release.database.user");
        String releasePassword = props.getProperty("release.database.password");
        String releaseHost = props.getProperty("release.database.host");
        int releasePort = Integer.valueOf(props.getProperty("release.database.port"));
        String releaseCurrent = props.getProperty("release_current.name");

        MySQLAdaptor dbAdaptor = new MySQLAdaptor(releaseHost, releaseCurrent, releaseUsername, releasePassword, releasePort);

        logger.info("Executing post-step checks");
        PostStepChecks.performStableIdentifierHistoryQA(dbAdaptor);
        logger.info("Finished post-step checks");
    }
}
