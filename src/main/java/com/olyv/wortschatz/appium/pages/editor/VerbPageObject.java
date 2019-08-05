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

    public void enterPartizip(String partizip) {
        verbPartizip.sendKeys(partizip);
    }

    public void selectAuxVerb(Auxverb auxverb) {
        if (auxverb == Auxverb.HAT) {
            auxverbHat.click();
        } else {
            auxverbIst.click();
        }
    }
}
