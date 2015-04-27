package com.olyv.wortschatz.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URL;

public class BaseTest
{
    protected AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception
    {
        File app = new File("./src/main/resources", "app-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities().android();
        capabilities.setCapability("deviceName", "foo");//"Nexus 4");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.olyv.wortschatz.ui");
        capabilities.setCapability("appActivity", ".StartActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterClass
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}
