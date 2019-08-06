package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Auxverb;
import com.olyv.wortschatz.appium.entity.Verb;
import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.entity.WordType;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import com.olyv.wortschatz.appium.pages.editor.AdjectivePageObject;
import com.olyv.wortschatz.appium.pages.editor.VerbPageObject;
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
    private AdjectivePageObject adjectivePageObject;
    private VerbPageObject verbPageObject;

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
        this.startScreen.openManager();
        this.managerScreen = ListOfWordsScreen.init(driver);
        this.managerScreen.search(word.getWord());
        this.managerScreen.deleteWord(driver);
    }

    private void givenManagerScreenIsOpened() {
        this.startScreen = StartScreen.init(driver);
        this.startScreen.openManager();
        this.managerScreen = ListOfWordsScreen.init(driver);
    }

    private void givenAdjectiveIsAddedViaEditor(Word adjective){
        this.startScreen = StartScreen.init(driver);
        this.editorScreen = startScreen.openEditor(driver);
        this.editorScreen.clickSpinner();
        this.adjectivePageObject = (AdjectivePageObject) this.editorScreen.selectSpinnerValue(driver, WordType.ADJECTIVE);
        this.adjectivePageObject.enterWord(adjective.getWord());
        this.adjectivePageObject.enterTranslation(adjective.getTranslation());
        this.adjectivePageObject.saveWord();
    }

    private void givenVerbIsAddedViaEditor(Verb verb){
        this.startScreen = StartScreen.init(driver);
        this.editorScreen = this.startScreen.openEditor(driver);

        this.editorScreen.clickSpinner();
        this.verbPageObject = (VerbPageObject) this.editorScreen.selectSpinnerValue(driver, WordType.VERB);
        this.verbPageObject.enterWord(verb.getWord());
        this.verbPageObject.enterTranslation(verb.getTranslation());
        this.verbPageObject.enterPartizip(verb.getPartizip());
        this.verbPageObject.selectAuxVerb(verb.getAuxverb());
        this.verbPageObject.saveWord();
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
        this.managerScreen.search(word);
    }

    private void thenSearchReturnedNumberOfWords(int expectedNumberOdFoundWords) {
        assertEquals(this.managerScreen.quantityOfFoundWords(), expectedNumberOdFoundWords);
    }
}
