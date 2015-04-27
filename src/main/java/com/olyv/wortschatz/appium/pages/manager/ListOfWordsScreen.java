package com.olyv.wortschatz.appium.pages.manager;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ListOfWordsScreen
{
    private static final Logger logger = LogManager.getLogger(ListOfWordsScreen.class.getName());

    @FindBy(id = "action_search")
    private WebElement searchWidgteIcon;

    @FindBy(id = "foundItem")
    private List<WebElement> listOfResults;

    @FindBy(id = "android:id/title")
    private WebElement deleteWordMenu;

    @FindBy(id = "android:id/button1")
    private WebElement dialogOkButton;

    @FindBy(id = "android:id/search_close_btn")
    private WebElement clearSearch;

    public void search(String criterion)
    {
        searchWidgteIcon.click();
        searchWidgteIcon.sendKeys(criterion);
        logger.info("entered search criterion " + criterion);
    }

    public int quantityOfFoundWords()
    {
        return listOfResults.size();
    }

    public void deleteWord(AppiumDriver driver)
    {
        WebElement foundWord = listOfResults.get(0);
        TouchAction action = new TouchAction(driver);
        action.longPress(foundWord).perform();
        deleteWordMenu.click();
        dialogOkButton.click();
        //to make further search working we have to clear search criterion
        //appium doesn't clear text field
        clearSearch.click();
        logger.info("word is deleted");
    }
}
