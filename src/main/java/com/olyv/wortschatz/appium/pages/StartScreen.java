package com.olyv.wortschatz.appium.pages;

import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import com.olyv.wortschatz.appium.pages.manager.ListOfWordsScreen;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartScreen
{
    private static final Logger logger = LogManager.getLogger(StartScreen.class.getName());

    @FindBy(id = "addLessonItemBtn")
    private WebElement newWordButton;

    @FindBy(id = "manageLessonItemsBtn")
    private WebElement manageWordsButton;

    @FindBy(id = "settings")
    private WebElement settingsActionBarMenu;

    public EditorScreen openEditor(AppiumDriver driver)
    {
        newWordButton.click();
        logger.info("returning EditorScreen");
        return PageFactory.initElements(driver, EditorScreen.class);
    }

    public boolean assertStartScreenDisplayed()
    {
        try
        {
            return settingsActionBarMenu.isDisplayed();
        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public ListOfWordsScreen openManager(AppiumDriver driver)
    {
        manageWordsButton.click();
        return PageFactory.initElements(driver, ListOfWordsScreen.class);
    }
}
