package driver.browser;

import driver.Platform;
import global.Variables;

/**
 * @author hongle
 * This class provide all of setup necessary step to set up chrome driver
 */
public class Chrome {

    private  String chromeDriver;

    public Chrome(String platform)
    {
        createChrome(platform);
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver(String chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public void createChrome (String platform)
    {

        if (platform.toUpperCase().startsWith(Platform.WINDOWS.toString()))
        {
            setChromeDriver(Variables.WIN_CHROME_DRIVER);
        }
        if (platform.toUpperCase().startsWith(Platform.MAC.toString()))
        {
            setChromeDriver(Variables.OS_CHROME_DRIVER);
        }
        else
            setChromeDriver(Variables.OS_CHROME_DRIVER);

    }


    public void setProperty ()
    {
        System.setProperty("webdriver.chrome.driver",getChromeDriver());
    }

}
