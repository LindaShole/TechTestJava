package util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceLoader.class);

    private static final String PROPERTIES_FILE = "/TechTest/anycompany/src/main/java/za.co.anycompany/resources/config.properties";

    public static Map<String,String> configs(){

        Map<String,String> dbConfigs = new HashMap<>();

        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            dbConfigs.put("DB_CONNECTION",prop.getProperty("DB_CONNECTION"));
            dbConfigs.put("DB_USER",prop.getProperty("DB_USER"));
            dbConfigs.put("DB_PASSWORD",prop.getProperty("DB_PASSWORD"));
            dbConfigs.put("DB_DRIVER",prop.getProperty("DB_DRIVER"));

        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
     return dbConfigs;
    }
}
