package framework.utils;
import java.util.ResourceBundle;


public class TestDataReader {

    //private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("dev"));

    public static String getTestData(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dev");
        return resourceBundle.getString(key);
    }
}
