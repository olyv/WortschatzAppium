package com.olyv.wortschatz.appium.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class Driver {

    private static final String PATH_TO_APK = "./src/main/resources";
    private static final String APK_NAME = "app-debug.apk";
    private static final String APP_PACKAGE_VALUE = "com.olyv.wortschatz.ui";
    private static final String APP_ACTIVITY_VALUE = ".StartActivity";
    private static final File APPLICATION_FILE = new File(PATH_TO_APK, APK_NAME);
    private static final long DRIVER_IMPLICIT_TIMEOUT_IN_SECONDS = 30;
    private static final String SKIP_UNLOCK = "skipUnlock";
    private static final String DISABLE_WINDOW_ANIMATION = "disableWindowAnimation";

    public static AppiumDriver getAndroidDriverInstance() throws MalformedURLException {
        AndroidDriver androidDriver = new AndroidDriver(new URL(AppiumService.getAppiumServerAddress()), getAndroidCapabilities());
        androidDriver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        return androidDriver;
    }

    private static DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(StringUtils.EMPTY);
        capabilities.setCapability(PLATFORM_NAME, "Android");
        capabilities.setCapability(PLATFORM_VERSION, "8.0");
        capabilities.setCapability(DEVICE_NAME, "emulator-api-26");
        capabilities.setCapability(AVD, "emulator-api-26");
        capabilities.setCapability(APP, APPLICATION_FILE.getAbsolutePath());
        capabilities.setCapability(APP_WAIT_PACKAGE, APP_PACKAGE_VALUE);
        capabilities.setCapability(APP_WAIT_ACTIVITY, APP_ACTIVITY_VALUE);
        capabilities.setCapability(AVD_LAUNCH_TIMEOUT, 300000);
        capabilities.setCapability(AVD_READY_TIMEOUT, 300000);
        capabilities.setCapability(NEW_COMMAND_TIMEOUT, 120);
        capabilities.setCapability(AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(CLEAR_SYSTEM_FILES, true);
        capabilities.setCapability(FULL_RESET, true);
        capabilities.setCapability((SKIP_UNLOCK), true);
        capabilities.setCapability((DISABLE_WINDOW_ANIMATION), true);
        capabilities.setCapability(ANDROID_DEVICE_READY_TIMEOUT, 120);
        capabilities.setCapability(AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(AVD_ARGS, "-no-boot-anim");
        return capabilities;
    }
}
