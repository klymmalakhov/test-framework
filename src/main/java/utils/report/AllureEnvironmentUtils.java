package utils.report;

import org.apache.tika.io.IOUtils;
import utils.properties.PropertyHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

public class AllureEnvironmentUtils {

    private static final File file = new File("target/allure-results/environment.properties");

    public static void create() {

        if (!file.exists()) {

            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(file);

                Properties props = new Properties();
                props.setProperty("Base URI", PropertyHolder.getPropValue("server.base.uri"));
                props.setProperty("Suite", getPropertiesValue("suite"));
                props.setProperty("Thread count", getPropertiesValue("threadCount"));

                props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");

                fos.close();
            } catch (IOException e) {
                System.out.println("IO problem when writing allure properties file: " + e);
            } finally {
                IOUtils.closeQuietly(fos);
            }
        }
    }

    private static String getPropertiesValue(String propKey) {
        String propertyValue = getProperty(propKey);
        if (propertyValue != null) {
            return propertyValue;
        } else {
            return "Property value is empty";
        }
    }

}