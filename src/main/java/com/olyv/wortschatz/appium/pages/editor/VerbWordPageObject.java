package com.olyv.wortschatz.appium.pages.editor;

import com.olyv.wortschatz.appium.entity.Auxverb;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerbWordPageObject extends EditorScreen implements IEditor {
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

    public VerbWordPageObject enterWord(String word) {
        wordField.sendKeys(word);
        logger.info("entering " + word);
        return this;
    }

    public VerbWordPageObject enterTranslation(String translation) {
        translationField.sendKeys(translation);
        logger.info("entering " + translation);
        return this;
    }

    public VerbWordPageObject enterPartizip(String partizip) {
        verbPartizip.sendKeys(partizip);
        logger.info("entering " + partizip);
        return this;
    }

    public VerbWordPageObject selectAuxVerb(Auxverb auxverb) {
        if (auxverb == Auxverb.HAT) {
            auxverbHat.click();
        } else {
            auxverbIst.click();
        }
        logger.info("selected " + auxverb);
        return this;
    }

    public VerbWordPageObject saveWord() {
        saveButton.click();
        logger.info("saving entered word");
        return this;
    }
}
