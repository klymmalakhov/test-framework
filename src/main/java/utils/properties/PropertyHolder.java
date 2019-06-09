package utils.properties;

import java.io.IOException;
import java.io.InputStream;

public class PropertyHolder {

    /**
     * The method works with config property file and gives the value of each property that is located here
     *
     * @param propertyName name of seeked property
     * @return value of seeked property
     */
    public static String getPropValue(String propertyName) {
        String namePropertyFile = "config.properties";
        InputStream inputStream = PropertyHolder.class.getClassLoader().getResourceAsStream(namePropertyFile);
        java.util.Properties prop = new java.util.Properties();
        String name = propertyName.replaceAll(" ", "_");
        try {
            prop.load(inputStream);
            return prop.getProperty(name);
        } catch (IOException e) {
            String errorMessage = "Can not find property name '" + propertyName + "'. Check it please.\n";
            throw new RuntimeException(errorMessage + e);
        }
    }
}