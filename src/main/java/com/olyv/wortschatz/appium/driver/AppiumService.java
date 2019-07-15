package com.olyv.wortschatz.appium.driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumService {

    private static final String APPIUM_SERVER_ADDRESS = "0.0.0.0";
    private static final int APPIUM_SERVER_PORT = 4724;
    private static final String NODEJS_PATH = System.getProperty("nodejsPath");
    private static final String APPIUM_PATH = System.getProperty("appiumPath");

    public static AppiumDriverLocalService getAppiumServiceInstance() {
        File nodeJs = new File(NODEJS_PATH);

        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(nodeJs)
                .withAppiumJS(new File((APPIUM_PATH)))
                .withIPAddress(APPIUM_SERVER_ADDRESS)
                .usingPort(APPIUM_SERVER_PORT));
    }

    public static void startAppiumServerIfNotRunning(AppiumDriverLocalService appiumService) {
        if (!appiumService.isRunning()) {
            appiumService.start();
        }
    }

    static String getAppiumServerAddress() {
        return String.format("http://%s:%d/wd/hub", APPIUM_SERVER_ADDRESS, APPIUM_SERVER_PORT);
    }
}
