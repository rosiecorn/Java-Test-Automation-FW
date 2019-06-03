package base;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import driver.CreateDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtils;


public class BaseBrowserPO {

    public BaseBrowserPO() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(CreateDriver.getInstance().getDriver()), this);

    }


    /**
     * getTitle - method to return the title of the current page
     *
     * @throws Exception
     */
    public String getTitle() {

        return CreateDriver.getInstance().getCurrentDriver().getTitle();
    }

    /**
     * loadPage - overloaded method to load the page URL and sync  *
     * * @param pageURL
     * * @throws Exception
     */
    public void openPage(String pageURL) {

        WebDriver driver = CreateDriver.getInstance().getCurrentDriver();
        driver.navigate().to(pageURL);

        BrowserUtils.isPageReady(driver);
    }

    public String getCurrentURL() {

        return CreateDriver.getInstance().getCurrentDriver().getCurrentUrl();
    }

}
