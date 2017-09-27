package com.olyv.wortschatz.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URL;

import static org.openqa.selenium.remote.DesiredCapabilities.android;

public class BaseTest {

    private static final String PATH_TO_APK = "./src/main/resources";
    private static final String APK_NAME = "app-debug.apk";
    private static final String DEVICE_NAME_CAPABILITY = "deviceName";
    private static final String DEVICE_NAME_VALUE = "ZH8001XCCC";
    private static final String PLATFORM_NAME_CAPABILITY = "platformName";
    private static final String PLATFORM_NAME_VALUE = "android";
    private static final String PLATFORM_VERSION_CAPABILITY = "platformVersion";
    private static final String PLATFORM_VERSION = "4.2.2";
    private static final String APP_PACKAGE_CAPABILITY = "appPackage";
    private static final String APP_PACKAGE_VALUE = "com.olyv.wortschatz.ui";
    private static final String APP_ACTIVITY_CAPABILITY = "appActivity";
    private static final String APP_ACTIVITY_VALUE = ".StartActivity";
    private static final String DRIVER_REMOTE_ADDRESS = "http://127.0.0.1:4723/wd/hub";
    private static final String APPLICATION_FILE_CAPABILITY = "app";
    private static final File APPLICATION_FILE = new File(PATH_TO_APK, APK_NAME);
    AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
       driver = new AndroidDriver(new URL(DRIVER_REMOTE_ADDRESS), getAndroidCapabilities());
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = android();
        capabilities.setCapability(DEVICE_NAME_CAPABILITY, DEVICE_NAME_VALUE);
        capabilities.setCapability(PLATFORM_NAME_CAPABILITY, PLATFORM_NAME_VALUE);
        capabilities.setCapability(PLATFORM_VERSION_CAPABILITY, PLATFORM_VERSION);
        capabilities.setCapability(APPLICATION_FILE_CAPABILITY, APPLICATION_FILE.getAbsolutePath());
        capabilities.setCapability(APP_PACKAGE_CAPABILITY, APP_PACKAGE_VALUE);
        capabilities.setCapability(APP_ACTIVITY_CAPABILITY, APP_ACTIVITY_VALUE);
        return capabilities;
    }
}
