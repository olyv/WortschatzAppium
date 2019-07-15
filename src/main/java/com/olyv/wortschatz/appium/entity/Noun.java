package com.olyv.wortschatz.appium.entity;

public class Noun extends Word {

    private Article article;
    private String plural;

    public Article getArticle() {
        return article;
    }

    public Noun setArticle(Article article) {
        this.article = article;
        return this;
    }

    public String getPlural() {
        return plural;
    }

    public Noun setPlural(String plural) {
        this.plural = plural;
        return this;
    }

    @Override
    public Noun setWord(String word) {
        this.word = word;
        return this;
    }

    @Override
    public Noun setTranslation(String translation) {
        this.translation = translation;
        return this;
    }
}
