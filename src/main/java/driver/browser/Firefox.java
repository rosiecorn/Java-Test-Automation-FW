package driver.browser;

import driver.Platform;
import global.Variables;

/**
 * @author hongle
 * This class provide all of setup necessary step to set up firefox driver
 */
public class Firefox {

    private  String firefoxDriver;

    public Firefox(String platform)
    {
        createFirefox(platform);
    }

    public String getFirefoxDriver() {
        return firefoxDriver;
    }

    public void setFirefoxDriver(String firefoxDriver) {
        this.firefoxDriver = firefoxDriver;
    }


    public void createFirefox (String platform)
    {
        if (platform.toUpperCase().startsWith(Platform.WINDOWS.toString()))
        {
            setFirefoxDriver(Variables.WIN_FIREFOX_DRIVER);
        }
        if (platform.toUpperCase().startsWith(Platform.MAC.toString()))
        {
            setFirefoxDriver(Variables.OS_FIREFOX_DRIVER);
        }
        else
            setFirefoxDriver(Variables.OS_FIREFOX_DRIVER);
    }

    public void setProperty ()
    {
        System.setProperty("webdriver.gecko.driver",getFirefoxDriver());
    }

}
