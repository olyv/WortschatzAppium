package com.olyv.wortschatz.appium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log
{
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message)
    {
        Log.info(message);
    }

    public static void warn(String message)
    {
        Log.warn(message);
    }

    public static void error(String message)
    {
        Log.error(message);
    }
}