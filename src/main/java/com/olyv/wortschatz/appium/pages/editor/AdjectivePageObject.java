package com.olyv.wortschatz.appium.pages.editor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdjectivePageObject extends EditorScreen implements IEditor {

    @FindBy(id = "adjektive")
    private WebElement wordField;

    @FindBy(id = "adjektiveTranslation")
    private WebElement translationField;

    @Override
    public void enterWord(String word) {
        wordField.sendKeys(word);
    }

    @Override
    public void enterTranslation(String translation) {
        translationField.sendKeys(translation);
    }

    @Override
    public void saveWord() {
        saveButton.click();
    }
}
