package com.olyv.wortschatz.appium.pages.editor;

public interface IEditor {
    IEditor enterWord(String word);

    IEditor enterTranslation(String translation);

    IEditor saveWord();
}
