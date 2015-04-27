package com.olyv.wortschatz.appium.pages.manager;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ListOfWordsScreen
{
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

    public void search(String criteria)
    {
        searchWidgteIcon.click();
        searchWidgteIcon.sendKeys(criteria);
    }

    public int quantityOfFoundWords(AppiumDriver driver)
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
    }
}
