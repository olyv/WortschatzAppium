package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Auxverb;
import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import com.olyv.wortschatz.appium.pages.editor.TranslationWordPageObject;
import com.olyv.wortschatz.appium.pages.editor.VerbWordPageObject;
import com.olyv.wortschatz.appium.pages.manager.ListOfWordsScreen;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.testng.Assert.assertEquals;

public class WordManagerTest extends BaseTest {
    private static final String SOME_NON_EXISTING_WORD = "12345%^";
    private StartScreen startScreen;
    private ListOfWordsScreen managerScreen;
    private EditorScreen editorScreen;
    private TranslationWordPageObject translationWordEditor;
    private VerbWordPageObject verbWordPageObject;

    @Test
    public void testSearchNonExistingWord() throws Exception {
        whenManagerScreenIsOpened();
        whenSearchForWord(SOME_NON_EXISTING_WORD);

        assertThatSearchReturnedNumberOfWords(0);
    }

    @Test
    public void testSearchSingleWord() {
        Word givenAdjective = givenAdjective();

        whenAdjectiveIsAddedViaEditor(givenAdjective);
        whenManagerScreenIsOpened();
        whenSearchForWord(givenAdjective.getWord());

        assertThatSearchReturnedNumberOfWords(1);
    }

    @Test
    public void testDeleteWord() {
        Word givenVerbWord = givenVerb();

        whenVerbIsAddedViaEditor(givenVerbWord);
        whenManagerScreenIsOpened();
        whenSearchForWord(givenVerbWord.getWord());

        assertThatSearchReturnedNumberOfWords(1);

        managerScreen.deleteWord(driver);
        whenSearchForWord(givenVerbWord.getWord());

        assertThatSearchReturnedNumberOfWords(0);
    }

    private void whenManagerScreenIsOpened() {
        startScreen = PageFactory.initElements(driver, StartScreen.class);
        managerScreen = startScreen.openManager(driver);
    }

    private void whenAdjectiveIsAddedViaEditor(Word adjective){
        startScreen = PageFactory.initElements(driver, StartScreen.class);
        editorScreen = startScreen.openEditor(driver);
        editorScreen.clickSpinner();
        translationWordEditor = (TranslationWordPageObject) editorScreen
                .selectSpinnerValue(driver, Word.TRANSLATION_TYPE)
                .enterWord(adjective.getWord())
                .enterTranslation(adjective.getTranslation())
                .saveWord(driver);
    }

    private void whenVerbIsAddedViaEditor(Word verb){
        startScreen = PageFactory.initElements(driver, StartScreen.class);
        editorScreen = startScreen.openEditor(driver);
        editorScreen.clickSpinner();
        verbWordPageObject = (VerbWordPageObject) editorScreen.selectSpinnerValue(driver, Word.VERB_TYPE);
        verbWordPageObject.enterWord(verb.getWord())
                .enterPartizip(verb.getPartizip())
                .selectAuxVerb(verb.getAuxverb())
                .enterTranslation(verb.getTranslation())
                .saveWord(driver);
    }

    private Word givenAdjective() {
        String uniqueWord = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return new Word()
                .setWord(uniqueWord)
                .setTranslation(uniqueWord);
    }

    private Word givenVerb() {
        String uniqueWord = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return new Word()
                .setWord(uniqueWord)
                .setTranslation(uniqueWord)
                .setAuxverb(Auxverb.HAT)
                .setPartizip(uniqueWord);
    }

    private void whenSearchForWord(String word) {
        managerScreen.search(word);
    }

    private void assertThatSearchReturnedNumberOfWords(int expectedNumberOdFoundWords) {
        assertEquals(managerScreen.quantityOfFoundWords(), expectedNumberOdFoundWords);
    }
}
