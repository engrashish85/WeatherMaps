package main.java.CommonFunctions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class IOFunctions {

    public String readValueFromPropertiesFile(String filePath, String valueName) throws IOException {
        FileReader reader = new FileReader(filePath);
        Properties properties = new Properties();
        properties.load(reader);
        return properties.getProperty(valueName);
    }
}
