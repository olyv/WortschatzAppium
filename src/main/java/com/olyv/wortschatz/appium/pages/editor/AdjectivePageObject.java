package com.olyv.wortschatz.appium.pages.editor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdjectivePageObject extends EditorScreen implements IEditor {
    @FindBy(id = "adjektive")
    private WebElement wordField;

    @FindBy(id = "adjektiveTranslation")
    private WebElement translationField;

    public AdjectivePageObject enterWord(String word) {
        wordField.sendKeys(word);
        return this;
    }

    public AdjectivePageObject enterTranslation(String translation) {
        translationField.sendKeys(translation);
        return this;
    }

    public AdjectivePageObject saveWord() {
        saveButton.click();
        return this;
    }
}
