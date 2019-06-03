package base;

import driver.CreateDriver;
import global.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class BaseBrowserTest {


    protected Logger logger;
    protected Method method;

    @Parameters({"browser", "platform", "environment"})
    @BeforeMethod
    public void setup(@Optional(Variables.BROWSER) String browser, @Optional(Variables.PLATFORM) String
            platform, @Optional(Variables.ENVIRONMENT) String env, Method method){

       // logger = LoggerFactory.getLogger(method.getDeclaringClass().getName());
        logger.info("Starting {}", method.getName());
        this.method = method;
        CreateDriver.getInstance().setDriver(browser,platform,env) ;
    }


    @AfterMethod
    public void teardown(ITestResult result) {
        String status = result.isSuccess() ? "Passed" : "Failed";
        logger.info("End {} -  {}",method.getName(),status );
        CreateDriver.getInstance().closeDriver();
    }



}
