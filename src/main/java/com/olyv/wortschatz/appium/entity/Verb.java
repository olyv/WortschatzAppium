package com.olyv.wortschatz.appium.entity;

public class Verb extends Word {

    private Auxverb auxverb;
    private String partizip;

    public Auxverb getAuxverb() {
        return auxverb;
    }

    public Verb setAuxverb(Auxverb auxverb) {
        this.auxverb = auxverb;
        return this;
    }

    public String getPartizip() {
        return partizip;
    }

    public Verb setPartizip(String partizip) {
        this.partizip = partizip;
        return this;
    }

    @Override
    public Verb setWord(String word) {
        this.word = word;
        return this;
    }

    @Override
    public Verb setTranslation(String translation) {
        this.translation = translation;
        return this;
    }
}
