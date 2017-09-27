package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Auxverb;
import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import com.olyv.wortschatz.appium.pages.editor.TranslationWordPageObject;
import com.olyv.wortschatz.appium.pages.editor.VerbWordPageObject;
import com.olyv.wortschatz.appium.pages.manager.ListOfWordsScreen;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.testng.Assert.assertEquals;

public class WordManagerTest extends BaseTest {
    private StartScreen startScreen;
    private ListOfWordsScreen managerScreen;
    private EditorScreen editorScreen;
    private TranslationWordPageObject translationWordEditor;
    private VerbWordPageObject verbWordPageObject;

    @Test
    public void testSearchNonExistingWord() throws Exception {
        startScreen = PageFactory.initElements(driver, StartScreen.class);
        managerScreen = startScreen.openManager(driver);
        managerScreen.search("12345%^");

        assertEquals(managerScreen.quantityOfFoundWords(), 0);
    }

    @Test
    public void testSearchSingleWord() {
        String uniqueWord = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Word newAdjektive = new Word()
                .setWord(uniqueWord)
                .setTranslation(uniqueWord);

        startScreen = PageFactory.initElements(driver, StartScreen.class);
        editorScreen = startScreen.openEditor(driver);
        editorScreen.clickSpinner(driver);
        translationWordEditor = (TranslationWordPageObject) editorScreen
                .selectSpinnerValue(driver, Word.TRANSLATION_TYPE)
                .enterWord(newAdjektive.getWord())
                .enterTranslation(newAdjektive.getTranslation())
                .saveWord();
        managerScreen = startScreen.openManager(driver);
        managerScreen.search(uniqueWord);

        assertEquals(managerScreen.quantityOfFoundWords(), 1);
    }

    @Test
    public void testDeleteWord() {
        String uniqueWord = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Word newVerb = new Word()
                .setWord(uniqueWord)
                .setTranslation(uniqueWord)
                .setAuxverb(Auxverb.HAT)
                .setPartizip(uniqueWord);

        startScreen = PageFactory.initElements(driver, StartScreen.class);
        editorScreen = startScreen.openEditor(driver);
        editorScreen.clickSpinner(driver);
        verbWordPageObject = (VerbWordPageObject) editorScreen.selectSpinnerValue(driver, Word.VERB_TYPE);
        verbWordPageObject.enterWord(newVerb.getWord())
                .enterPartizip(newVerb.getPartizip())
                .selectAuxVerb(newVerb.getAuxverb())
                .enterTranslation(newVerb.getTranslation())
                .saveWord();
        managerScreen = startScreen.openManager(driver);
        managerScreen.search(uniqueWord);

        assertEquals(managerScreen.quantityOfFoundWords(), 1);

        managerScreen.deleteWord(driver);
        managerScreen.search(uniqueWord);

        assertEquals(managerScreen.quantityOfFoundWords(), 0);
    }
}
