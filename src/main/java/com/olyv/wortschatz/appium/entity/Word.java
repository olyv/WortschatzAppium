package com.olyv.wortschatz.appium.entity;

public class Word {

    protected String word;
    protected String translation;

    public String getWord() {
        return word;
    }

    public Word setWord(String word) {
        this.word = word;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public Word setTranslation(String translation) {
        this.translation = translation;
        return this;
    }
}
