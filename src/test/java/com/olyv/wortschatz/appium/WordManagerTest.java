package com.olyv.wortschatz.appium;

import com.olyv.wortschatz.appium.entity.Auxverb;
import com.olyv.wortschatz.appium.entity.Verb;
import com.olyv.wortschatz.appium.entity.Word;
import com.olyv.wortschatz.appium.entity.WordType;
import com.olyv.wortschatz.appium.pages.StartScreen;
import com.olyv.wortschatz.appium.pages.editor.AdjectivePageObject;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
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
    private Verb someVerb;
    private Word someAdjective;

    @Test
    public void testSearchNonExistingWord() {
        givenManagerScreenIsOpened();
        whenSearchForWord(SOME_NON_EXISTING_WORD);
        thenSearchReturnedNumberOfWords(0);
    }

    @Test
    public void testSearchSingleWord() {
        wordIsAddedViaEditor(WordType.ADJECTIVE);
        givenManagerScreenIsOpened();
        whenSearchForWord(someAdjective.getWord());
        thenSearchReturnedNumberOfWords(1);
    }

    @Test
    public void testDeleteWord() {
        wordIsAddedViaEditor(WordType.VERB);
        whenWordDeletedAfterSearch(someVerb);
        thenSearchReturnedNumberOfWords(0);
    }

    private void wordIsAddedViaEditor(WordType type) {
        switch (type) {
            case ADJECTIVE:
                someAdjective = getSomeAdjective();
                addAdjectiveViaEditor(someAdjective);
                break;
            case VERB:
                someVerb = getSomeVerb();
                addVerbViaEditor(someVerb);
                break;
        }
    }

    private void openEditor() {
        this.startScreen = StartScreen.init(driver);
        this.editorScreen = this.startScreen.openEditor(driver);
        this.editorScreen.clickSpinner();
    }

    private void addAdjectiveViaEditor(Word adjective) {
        openEditor();
        this.adjectivePageObject = (AdjectivePageObject) this.editorScreen.selectSpinnerValue(driver, WordType.ADJECTIVE);
        this.adjectivePageObject.enterWord(adjective.getWord());
        this.adjectivePageObject.enterTranslation(adjective.getTranslation());
        this.adjectivePageObject.saveWord();
    }

    private void addVerbViaEditor(Verb verb) {
        openEditor();
        this.verbPageObject = (VerbPageObject) this.editorScreen.selectSpinnerValue(driver, WordType.VERB);
        this.verbPageObject.enterWord(verb.getWord());
        this.verbPageObject.enterTranslation(verb.getTranslation());
        this.verbPageObject.enterPartizip(verb.getPartizip());
        this.verbPageObject.selectAuxVerb(verb.getAuxverb());
        this.verbPageObject.saveWord();
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
