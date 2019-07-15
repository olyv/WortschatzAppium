package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.driver.AppiumService;
import com.olyv.wortschatz.appium.driver.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class BaseTest {

    AppiumDriver driver;
    private AppiumDriverLocalService appiumService;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        appiumService = AppiumService.getAppiumServiceInstance();
        AppiumService.startAppiumServerIfNotRunning(appiumService);
        driver = Driver.getAndroidDriverInstance();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        appiumService.stop();
    }
}
