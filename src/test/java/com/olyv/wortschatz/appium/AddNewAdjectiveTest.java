package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import com.olyv.wortschatz.appium.pages.editor.TranslationWordPageObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddNewAdjectiveTest extends BaseTest {
    private StartScreen startScreen;
    private EditorScreen editorScreen;
    private TranslationWordPageObject translationWordEditor;

    @Test
    public void testAddNewAdjective() throws Exception {
        Word newAdjective = new Word()
                .setWord("testAdj")
                .setTranslation("testTransl");

        startScreen = PageFactory.initElements(driver, StartScreen.class);
        editorScreen = startScreen.openEditor(driver);
        editorScreen.clickSpinner();
        translationWordEditor = (TranslationWordPageObject) editorScreen
                .selectSpinnerValue(driver, Word.TRANSLATION_TYPE)
                .enterWord(newAdjective.getWord())
                .enterTranslation(newAdjective.getTranslation())
                .saveWord(driver);

        assertTrue(startScreen.assertStartScreenDisplayed());
    }
}
