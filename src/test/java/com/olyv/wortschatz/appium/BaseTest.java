package com.olyv.wortschatz.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.DesiredCapabilities.android;

public class BaseTest {

    private static final String PATH_TO_APK = "./src/main/resources";
    private static final String APK_NAME = "app-debug.apk";
    private static final String DEVICE_NAME_CAPABILITY = "deviceName";
    private static final String DEVICE_NAME_VALUE = "3204a166cff0b197";
    private static final String APP_PACKAGE_CAPABILITY = "appPackage";
    private static final String APP_PACKAGE_VALUE = "com.olyv.wortschatz.ui";
    private static final String APP_ACTIVITY_CAPABILITY = "appActivity";
    private static final String APP_ACTIVITY_VALUE = ".StartActivity";
    private static final String DRIVER_REMOTE_ADDRESS = "http://127.0.0.1:4723/wd/hub";
    private static final String APPLICATION_FILE_CAPABILITY = "app";
    private static final File APPLICATION_FILE = new File(PATH_TO_APK, APK_NAME);
    private static final String APPIUM_SERVER_ADDRESS = "0.0.0.0";
    private static final int APPIUM_SERVER_PORT = 4723;
    private static final String PATH_TO_NODEJS = "/home/olyv/nodejs/bin/node";
    private static final String PATH_TO_NODEJS_APPIUM = "/home/olyv/nodejs/bin/appium";
    private static final long DRIVER_IMPLICIT_TIMEOUT_IN_SECONDS = 30;

    AppiumDriver driver;
    private AppiumDriverLocalService appiumService;

    @BeforeClass
    public void setUp() throws Exception {
        appiumService = getAppiumService();
        startAppiumServerIfNotRunning(appiumService);
        driver = getAndroidDriver();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
        appiumService.stop();
    }

    private AppiumDriver getAndroidDriver() throws Exception {
        AndroidDriver androidDriver = new AndroidDriver(new URL(DRIVER_REMOTE_ADDRESS), getAndroidCapabilities());
        androidDriver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        return androidDriver;
    }

    private void startAppiumServerIfNotRunning(AppiumDriverLocalService appiumService) {
        if (!appiumService.isRunning()) {
            appiumService.start();
        }
    }

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = android();
        capabilities.setCapability(DEVICE_NAME_CAPABILITY, DEVICE_NAME_VALUE);
        capabilities.setCapability(APPLICATION_FILE_CAPABILITY, APPLICATION_FILE.getAbsolutePath());
        capabilities.setCapability(APP_PACKAGE_CAPABILITY, APP_PACKAGE_VALUE);
        capabilities.setCapability(APP_ACTIVITY_CAPABILITY, APP_ACTIVITY_VALUE);
        return capabilities;
    }

    private AppiumDriverLocalService getAppiumService() {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File((PATH_TO_NODEJS)))
                .withAppiumJS(new File((PATH_TO_NODEJS_APPIUM)))
                .withIPAddress(APPIUM_SERVER_ADDRESS)
                .usingPort(APPIUM_SERVER_PORT));
    }
}
