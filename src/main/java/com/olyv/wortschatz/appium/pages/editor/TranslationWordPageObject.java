package com.olyv.wortschatz.appium.pages.editor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TranslationWordPageObject extends EditorScreen implements IEditor
{
    @FindBy(id = "adjektive")
    private WebElement wordField;

    @FindBy(id = "adjektiveTranslation")
    private WebElement translationField;

    @Override
    public TranslationWordPageObject enterWord(String word)
    {
        wordField.sendKeys(word);
        logger.info("entering " + word);
        return this;
    }

    @Override
    public TranslationWordPageObject enterTranslation(String translation)
    {
        translationField.sendKeys(translation);
        logger.info("entering " + translation);
        return this;
    }

    @Override
    public TranslationWordPageObject saveWord()
    {
        saveButton.click();
        logger.info("saving entered word");
        return this;
    }
}
