package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.entity.WordType;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddNewAdjectiveTest extends BaseTest {

    private StartScreen startScreen;
    private EditorScreen editorScreen;

    @Test
    public void testAddNewAdjective() {
        goFromHomeScreenToEditor();
        enterAndSaveAdjective();
        assertTrue(this.startScreen.isScreenDisplayed());
    }

    private Word getSomeAdjective() {
        return new Word()
                .setWord("testAdj")
                .setTranslation("testTransl");
    }

    private void goFromHomeScreenToEditor() {
        this.startScreen = StartScreen.init(driver);
        this.startScreen.openEditor(driver);
        this.editorScreen = EditorScreen.init(driver);
    }

    private void enterAndSaveAdjective() {
        editorScreen.clickSpinner();
        var adjectivePageObject = editorScreen.selectSpinnerValue(driver, WordType.ADJECTIVE);
        var adjective = getSomeAdjective();
        adjectivePageObject.enterWord(adjective.getWord());
        adjectivePageObject.enterTranslation(adjective.getTranslation());
        adjectivePageObject.saveWord();

    }
}
