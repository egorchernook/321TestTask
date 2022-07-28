package org.test.etc;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to read the conf.properties file
 */
public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * method to read property
     * @param key - property name to read from file
     * @return property
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key); }
}