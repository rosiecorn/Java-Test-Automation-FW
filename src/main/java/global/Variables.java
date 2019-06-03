package global;

import java.io.File;
import java.util.Properties;
import setting.ExtractPropertyFiles;

/**
 * @author hongle
 */

public class Variables {

    private Variables (){}

    private static Properties setting = new ExtractPropertyFiles("src/test/resources/app.properties").getProperties();

    //Default value for Rest API Testing

    public static final String API_DATA_MAPPING = setting.getProperty("API_DATA_MAPPING");
    public static final String API_DATA_MAPPING_FILE_PATH = new File(API_DATA_MAPPING).getAbsolutePath();

    public static final String UI_DATA_MAPPING = setting.getProperty("UI_DATA_MAPPING");
    public static final String UI_DATA_MAPPING_FILE_PATH = new File(UI_DATA_MAPPING).getAbsolutePath();

    public static final String TEST_DATA_FILE = setting.getProperty("TEST_DATA_FILE");
    public static final String TEST_DATA_FILE_PATH = new File(TEST_DATA_FILE).getAbsolutePath();

    public static final String SQL_SCRIPT = setting.getProperty("SQLSCRIPT");
    public static final String SQL_SCRIPT_PATH = new File(SQL_SCRIPT).getAbsolutePath();

    public static final String SUITE = setting.getProperty("SUITE");
    public static final String SUITE_PATH = new File(SUITE).getAbsolutePath();

    public static final String CONN_STRING =setting.getProperty("CONNECTIONSTRING");
    public static final String USER =setting.getProperty("USER");
    public static final String PASSWORD =setting.getProperty("PASSWORD");


    // Target app default for UI Testing
    public static final String BROWSER = "chrome";
    public static final String PLATFORM = "mac";
    public static final String ENVIRONMENT = "Local";

    //Timer defaults
    public static final int TIMEOUT_MINUTE = 1;
    public static final int TIMEOUT_SECOND = 60;
    public static final  int TIMEOUT_ZERO = 0;


    //Get driver information bases on Platform
    public static final String OS_CHROME_DRIVER = setting.getProperty("OS.CHROME.DRIVER");
    public static final String WIN_CHROME_DRIVER = setting.getProperty("WIN.CHROME.DRIVER");

    public static final String OS_FIREFOX_DRIVER = setting.getProperty("OS.FIREFOX.DRIVER");
    public static final String WIN_FIREFOX_DRIVER = setting.getProperty("WIN.FIREFOX.DRIVER");

    public static final String OS_IE_DRIVER = setting.getProperty("OS.IE.DRIVER");
    public static final String WIN_IE_DRIVER = setting.getProperty("WIN.IE.DRIVER");

    public static final String OS_EDGE_DRIVER = setting.getProperty("OS.EDGE.DRIVER");
    public static final String WIN_EDGE_DRIVER = setting.getProperty("WIN.EDGE.DRIVER");



}
