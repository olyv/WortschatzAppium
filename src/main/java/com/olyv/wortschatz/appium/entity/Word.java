package com.olyv.wortschatz.appium.entity;

public class Word {
    public static final String TRANSLATION_TYPE = "Translation";
    public static final String VERB_TYPE = "Verb";
    public static final String NOUN_TYPE = "Noun";
    //common fields
    private String word;
    private String translation;

    //noun fields
    private Article article;
    private String plural;

    //verb fields
    private Auxverb auxverb;
    private String partizip;

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

    public Article getArticle() {
        return article;
    }

    public Word setArticle(Article article) {
        this.article = article;
        return this;
    }

    public Auxverb getAuxverb() {
        return auxverb;
    }

    public Word setAuxverb(Auxverb auxverb) {
        this.auxverb = auxverb;
        return this;
    }

    public String getPlural() {
        return plural;
    }

    public Word setPlural(String plural) {
        this.plural = plural;
        return this;
    }

    public String getPartizip() {
        return partizip;
    }

    public Word setPartizip(String partizip) {
        this.partizip = partizip;
        return this;
    }
}
