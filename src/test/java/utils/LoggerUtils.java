package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class LoggerUtils implements ITestListener {

    public Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

    @BeforeMethod
    public void startLogger(Method m) {
        logger.info("Start method: " + m.toString());
    }

    @AfterMethod
    public void stopLogger(Method m) {
        logger.info("Stop method: " + m.toString());
    }


}
