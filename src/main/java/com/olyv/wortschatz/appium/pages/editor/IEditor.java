package com.olyv.wortschatz.appium.pages.editor;

import io.appium.java_client.AppiumDriver;

public interface IEditor
{
    IEditor enterWord(String word);

    IEditor enterTranslation(String translation);

    IEditor saveWord(AppiumDriver driver);
}
