package com.olyv.wortschatz.appium.pages.manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ListOfWordsScreen {

    @FindBy(id = "action_search")
    private MobileElement searchWidgetIcon;

    @FindBy(id = "android:id/search_src_text")
    private MobileElement searchWidgetInput;

    @FindBy(id = "foundItemTranslation")
    private List<MobileElement> listOfResults;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Remove this record\")")
    private MobileElement deleteWordMenu;

    @FindBy(id = "android:id/button1")
    private MobileElement dialogOkButton;

    public static ListOfWordsScreen init(AppiumDriver driver) {
        var managerScreen = new ListOfWordsScreen();
        PageFactory.initElements(new AppiumFieldDecorator(driver), managerScreen);
        return managerScreen;
    }

    public void search(String criterion) {
        searchWidgetIcon.click();
        searchWidgetInput.setValue(criterion);
    }

    public int quantityOfFoundWords() {
        return listOfResults.size();
    }

    public void deleteWord(AppiumDriver driver) {
        longPressResult(driver, listOfResults.get(0));
        deleteWordMenu.click();
        dialogOkButton.click();
    }

    private void longPressResult(AppiumDriver driver, MobileElement element) {
        var point = getElementPointToPress(element);
        var action = new TouchAction(driver);
        action.longPress(point);
        action.perform();
    }

    private PointOption getElementPointToPress(MobileElement element) {
        return PointOption.point(element.getCenter().x, element.getCenter().y);
    }
}
