package base;

import api.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseServiceTest {

   public Logger logger;
   public Method method;

    @BeforeMethod
    public void beforeMethod(Method method) {
        RestService.getInstance().setRest();
        logger = LoggerFactory.getLogger(method.getDeclaringClass().getName());
        logger.info("Starting {}", method.getName());
        this.method = method;

    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult result) {

        String status = result.isSuccess() ? "Passed" : "Failed";
        logger.info("End {} -  {}",method.getName(),status );
    }


}