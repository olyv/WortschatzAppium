package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Auxverb;
import com.olyv.wortschatz.appium.entity.Verb;
import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.entity.WordType;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import com.olyv.wortschatz.appium.pages.editor.TranslationWordPageObject;
import com.olyv.wortschatz.appium.pages.editor.VerbWordPageObject;
import com.olyv.wortschatz.appium.pages.manager.ListOfWordsScreen;
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
    public void testSearchNonExistingWord() {
        givenManagerScreenIsOpened();

        whenSearchForWord(SOME_NON_EXISTING_WORD);

        thenSearchReturnedNumberOfWords(0);
    }

    @Test
    public void testSearchSingleWord() {
        var someAdjective = getSomeAdjective();
        givenAdjectiveIsAddedViaEditor(someAdjective);

        givenManagerScreenIsOpened();

        whenSearchForWord(someAdjective.getWord());

        thenSearchReturnedNumberOfWords(1);
    }

    @Test
    public void testDeleteWord() {
        var someVerb = getSomeVerb();

        givenVerbIsAddedViaEditor(someVerb);

        whenWordDeletedAfterSearch(someVerb);

        thenSearchReturnedNumberOfWords(0);
    }

    private void whenWordDeletedAfterSearch(Word word) {
        startScreen.openManager();
        managerScreen = ListOfWordsScreen.init(driver);
        managerScreen.search(word.getWord());
        managerScreen.deleteWord(driver);
    }

    private void givenManagerScreenIsOpened() {
        StartScreen.init(driver);
        startScreen.openManager();
        this.managerScreen = ListOfWordsScreen.init(driver);
    }

    private void givenAdjectiveIsAddedViaEditor(Word adjective){
        StartScreen.init(driver);
        editorScreen = startScreen.openEditor(driver);
        editorScreen.clickSpinner();
        translationWordEditor = (TranslationWordPageObject) editorScreen
                .selectSpinnerValue(driver, WordType.TRANSLATION)
                .enterWord(adjective.getWord())
                .enterTranslation(adjective.getTranslation())
                .saveWord();
    }

    private void givenVerbIsAddedViaEditor(Verb verb){
        startScreen = StartScreen.init(driver);
        editorScreen = startScreen.openEditor(driver);

        editorScreen.clickSpinner();
        verbWordPageObject = (VerbWordPageObject) editorScreen.selectSpinnerValue(driver, WordType.VERB);
        verbWordPageObject.enterWord(verb.getWord())
                .enterTranslation(verb.getTranslation())
                .saveWord()
                .enterPartizip(verb.getPartizip())
                .selectAuxVerb(verb.getAuxverb());
    }

    private Word getSomeAdjective() {
        String uniqueWord = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return new Word()
                .setWord(uniqueWord)
                .setTranslation(uniqueWord);
    }

    private Verb getSomeVerb() {
        String uniqueWord = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return new Verb()
                .setAuxverb(Auxverb.HAT)
                .setPartizip(uniqueWord)
                .setWord(uniqueWord)
                .setTranslation(uniqueWord);
    }

    private void whenSearchForWord(String word) {
        managerScreen.search(word);
    }

    private void thenSearchReturnedNumberOfWords(int expectedNumberOdFoundWords) {
        assertEquals(managerScreen.quantityOfFoundWords(), expectedNumberOdFoundWords);
    }
}
