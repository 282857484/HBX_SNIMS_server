package log.config;

import java.util.ResourceBundle;



public class LogConfig {
    static String configFile = "log4j";
    public static String getConfigInfomation(String itemIndex) {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(configFile);
            return resource.getString(itemIndex);
        } catch (Exception e) {
            return "";
        }
    }
}