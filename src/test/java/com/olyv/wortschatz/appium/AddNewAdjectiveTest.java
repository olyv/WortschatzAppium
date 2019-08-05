package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.entity.WordType;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddNewAdjectiveTest extends BaseTest {

    @Test
    public void testAddNewAdjective() {
        Word newAdjective = new Word()
                .setWord("testAdj")
                .setTranslation("testTransl");

        var startScreen = StartScreen.init(driver);
        startScreen.openEditor(driver);
        var editorScreen = EditorScreen.init(driver);
        editorScreen.clickSpinner();
        editorScreen.selectSpinnerValue(driver, WordType.ADJECTIVE)
                .enterWord(newAdjective.getWord())
                .enterTranslation(newAdjective.getTranslation())
                .saveWord();

        assertTrue(startScreen.isStartScreenDisplayed());
    }
}
