package driver;

import driver.browser.BrowserType;
import driver.browser.Chrome;
import driver.browser.Firefox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author hongle
 */

public class CreateDriver {

    private static CreateDriver instance = null;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private ThreadLocal<String> sessionId = new ThreadLocal<>();
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<>();

    // constructor
    private CreateDriver() {
    }

    /**
     * getInstance method to retrieve active driver instance
     *
     * @return CreateDriver
     */
    public static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    /**
     * getDriver method will retrieve the active WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return webDriver.get();
    }

    /**
     * getCurrentDriver method will retrieve the active WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getCurrentDriver() {
        return getInstance().getDriver();
    }


    /**
     * overloaded setDriver method to switch driver to specific WebDriver
     * * if running concurrent drivers
     * * @param driver WebDriver instance to switch to
     */
    public void setDriver(WebDriver driver) {
        webDriver.set(driver);
        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
        sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());
    }

    /**
     * driverRefresh method reloads the current browser page
     */
    public void driverRefresh() {
        getCurrentDriver().navigate().refresh();
    }

    /**
     * closeDriver method quits the current active driver
     */
    public void closeDriver() {
            getDriver().quit();
            webDriver.remove();
    }

    /**
     * overloaded setDriver method to create specific WebDriver using thread safe
     * @param browser
     * @param platform
     */
    public void setDriver( String browser, String platform, String env)
    {

        if (env.toUpperCase().equalsIgnoreCase(Environment.LOCAL.toString())) {
            if (browser.toUpperCase().equalsIgnoreCase(BrowserType.FIREFOX.toString())){
                    new Firefox(platform).setProperty();
                    webDriver.set(new FirefoxDriver());
                }
            if (browser.toUpperCase().equalsIgnoreCase(BrowserType.CHROME.toString())) {
                new Chrome(platform).setProperty();
                webDriver.set(new ChromeDriver());
            }
        }

    }
}


