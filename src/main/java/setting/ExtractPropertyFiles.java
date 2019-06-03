package setting;

import exception.CustomException;

import java.io.*;
import java.util.Properties;

/**
 * @author hongle
 * This class use to read from any properties file
 */

public class ExtractPropertyFiles {


    private Properties properties;

    public ExtractPropertyFiles(String propFilePath) {


        properties = new Properties();
        try {
            properties.load(new FileInputStream(propFilePath));
        } catch (IOException ex) {
            throw new CustomException("Properties not found at " + propFilePath);
        }

    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties =  properties;
    }


}

