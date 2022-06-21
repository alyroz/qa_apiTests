package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    private static final Properties PROPERTIES = new Properties();

    public static String getProperty(String parameter) {
        if(PROPERTIES.toString().equals("{}")) {
            initProperties();
        }
        return PROPERTIES.getProperty(parameter);
    }

    private static void initProperties() {
        try {
            PROPERTIES.load(new FileReader(new File("src/test/resources/config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
