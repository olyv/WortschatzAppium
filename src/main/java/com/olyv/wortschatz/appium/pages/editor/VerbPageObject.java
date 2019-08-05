package com.olyv.wortschatz.appium.pages.editor;

import com.olyv.wortschatz.appium.entity.Auxverb;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerbPageObject extends EditorScreen implements IEditor {
    @FindBy(id = "verb")
    private WebElement wordField;

    @FindBy(id = "verbTranslation")
    private WebElement translationField;

    @FindBy(id = "auxverbHat")
    private WebElement auxverbHat;

    @FindBy(id = "auxverbIst")
    private WebElement auxverbIst;

    @FindBy(id = "verbPartizip")
    private WebElement verbPartizip;

    public VerbPageObject enterWord(String word) {
        wordField.sendKeys(word);
        return this;
    }

    public VerbPageObject enterTranslation(String translation) {
        translationField.sendKeys(translation);
        return this;
    }

    public VerbPageObject enterPartizip(String partizip) {
        verbPartizip.sendKeys(partizip);
        return this;
    }

    public VerbPageObject selectAuxVerb(Auxverb auxverb) {
        if (auxverb == Auxverb.HAT) {
            auxverbHat.click();
        } else {
            auxverbIst.click();
        }
        return this;
    }

    public VerbPageObject saveWord() {
        saveButton.click();
        return this;
    }
}
