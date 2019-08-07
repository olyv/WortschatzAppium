package com.olyv.wortschatz.appium.pages;

import com.olyv.wortschatz.appium.pages.editor.EditorScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartScreen {

    @FindBy(id = "addLessonItemBtn")
    private WebElement newWordButton;
    @FindBy(id = "manageLessonItemsBtn")
    private WebElement manageWordsButton;
    @FindBy(id = "settings")
    private WebElement settingsActionBarMenu;
    private static StartScreen startScreen;

    public static StartScreen init(AppiumDriver driver) {
        if (startScreen == null) {
            startScreen = new StartScreen();
            PageFactory.initElements(new AppiumFieldDecorator(driver), startScreen);
        }
        return startScreen;
    }

    public EditorScreen openEditor(AppiumDriver driver) {
        newWordButton.click();
        return EditorScreen.init(driver);
    }

    public boolean isScreenDisplayed() {
        return settingsActionBarMenu.isDisplayed();
    }

    public void openManager() {
        manageWordsButton.click();
    }
}
