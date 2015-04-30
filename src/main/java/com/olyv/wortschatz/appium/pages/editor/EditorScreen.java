package com.olyv.wortschatz.appium.pages.editor;

import com.olyv.wortschatz.appium.entity.Word;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class EditorScreen
{
    protected static final Logger logger = LogManager.getLogger(EditorScreen.class.getName());

    @FindBy(id = "selectTypeSpinner")
    private WebElement wordTypeSpinner;

    @FindBy(id = "saveBtn")
    protected WebElement saveButton;

    public void clickSpinner(AppiumDriver driver)
    {
        wordTypeSpinner.click();
        logger.info("clicked wordtype spinner");
    }

    public IEditor selectSpinnerValue(AppiumDriver driver, String spinnerValue)
    {
        driver.findElementByName(spinnerValue).click();

        IEditor editorPageObject = null;

        if (spinnerValue.equals(Word.TRANSLATION_TYPE))
        {
            editorPageObject = PageFactory.initElements(driver, TranslationWordPageObject.class);
        }
        else if (spinnerValue.equals(Word.VERB_TYPE))
        {
            editorPageObject = PageFactory.initElements(driver, VerbWordPageObject.class);
        }
        else if (spinnerValue.equals(Word.NOUN_TYPE))
        {
            //TODO: change class
            editorPageObject = PageFactory.initElements(driver, TranslationWordPageObject.class);
        }
        logger.info("returned " + editorPageObject.getClass().getSimpleName());
        return editorPageObject;
    }
}
