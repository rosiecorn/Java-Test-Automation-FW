package ui;

import base.BaseBrowserTest;
import data.TestDataProvider;
import org.testng.annotations.Test;


public class LoginTest extends BaseBrowserTest {


    @Test(dataProvider="uiData", dataProviderClass = TestDataProvider.class, priority = 1)
    public void TC01_ValidLoginTest(String userName, String password)
    {
        LoginPO  page = new LoginPO();

        page.openPage(page.PHPURL);

        page.username.sendKeys(userName);
        page.password.sendKeys(password);
        page.btnLogin.click();

    }


    @Test(priority = 1)
    public void TC02_TableTest()
    {
        LoginPO  page = new LoginPO();

        page.openPage(page.NEWTOURURL);

        logger.info(" Size {}" ,page.table.getTableSize());
        logger.info("cell {}" ,page.table.getRow(2).getCell(2).getText());

    }


}
