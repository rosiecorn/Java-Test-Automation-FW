package utilities;

import driver.CreateDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * @author @HongLe
 * <p>
 * Browser Utility Class
 */
public class BrowserUtils {

    private BrowserUtils()
    {}
    /**
     * waitFor method to poll page title
     *
     * @param title
     * @param timer
     * @throws Exception
     */
    public static void waitFor(String title,
                               int timer)
             {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }
    /**
     * waitForURL method to poll page URL
     *
     * @param url
     * @param timer
     * @throws Exception
     */
    public static void waitForURL(String url,
                                  int timer)
    {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }

    /**
     * isPageReady - method to verify that a page has completely rendered  *  * @param driver  * @return boolean
     */
    public static boolean isPageReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return document.readyState").equals("complete");
    }
    /**
     * isAjaxReady - method to verify that an ajax control has rendered  *
     *
     * @param driver * @return boolean
     */
    public static boolean isAjaxReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return jQuery.active == 0");
    }


    public static void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) CreateDriver.getInstance().getDriver();
        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
    }
}