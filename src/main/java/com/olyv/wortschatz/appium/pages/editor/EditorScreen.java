package com.olyv.wortschatz.appium.pages.editor;

import com.olyv.wortschatz.appium.entity.Word;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditorScreen {
    @FindBy(id = "saveBtn")
    WebElement saveButton;

    @FindBy(id = "selectTypeSpinner")
    private WebElement wordTypeSpinner;

    public void clickSpinner() {
        wordTypeSpinner.click();
    }

    public IEditor selectSpinnerValue(AppiumDriver driver, String spinnerValue) {

        List<WebElement> spinnerEntries = driver
                .findElement(By.className("android.widget.ListView"))
                .findElements(By.className("android.widget.TextView"));

        for (WebElement entry : spinnerEntries) {
            if (entry.getText().equalsIgnoreCase(spinnerValue)) {
                entry.click();
            }
        }

        IEditor editorPageObject = null;

        if (spinnerValue.equals(Word.TRANSLATION_TYPE)) {
            editorPageObject = PageFactory.initElements(driver, TranslationWordPageObject.class);
        } else if (spinnerValue.equals(Word.VERB_TYPE)) {
            editorPageObject = PageFactory.initElements(driver, VerbWordPageObject.class);
        } else if (spinnerValue.equals(Word.NOUN_TYPE)) {
            //TODO: change class
            editorPageObject = PageFactory.initElements(driver, TranslationWordPageObject.class);
        }
        return editorPageObject;
    }
}
