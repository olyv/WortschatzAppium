package com.olyv.wortschatz.appium.entity;

public enum WordType {

    ADJECTIVE("Translation"),
    VERB("Verb"),
    NOUN("Noun");

    String spinnerValueInEditor;

    WordType(String spinnerValue) {
        this.spinnerValueInEditor = spinnerValue;
    }

    public String getSpinnerValueInEditor() {
        return spinnerValueInEditor;
    }
}
