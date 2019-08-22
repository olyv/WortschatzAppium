package com.olyv.wortschatz.appium.pages.editor;

import com.olyv.wortschatz.appium.entity.WordType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import static com.olyv.wortschatz.appium.entity.WordType.ADJECTIVE;
import static com.olyv.wortschatz.appium.entity.WordType.VERB;

public class EditorScreen {

    private final BiPredicate<WebElement, String> isElementWithText = (element, text) -> element.getText().equalsIgnoreCase(text);
    @FindBy(id = "saveBtn")
    MobileElement saveButton;
    @FindBy(id = "selectTypeSpinner")
    private MobileElement wordTypeSpinner;

    public static EditorScreen init(AppiumDriver driver) {
        var editorScreen = new EditorScreen();
        PageFactory.initElements(new AppiumFieldDecorator(driver), editorScreen);
        return editorScreen;
    }

    public void clickSpinner() {
        wordTypeSpinner.click();
    }

    public IEditor selectSpinnerValue(AppiumDriver driver, WordType spinnerValue) {

        var spinnerEntries = driver
                .findElement(By.className("android.widget.DropDownListView"))
                .findElements(By.className("android.widget.TextView"));

        spinnerEntries.stream()
                .filter(element -> isElementWithText.test(element, spinnerValue.getSpinnerValueInEditor()))
                .findFirst()
                .orElseThrow()
                .click();

        return getFragmentInstance(driver, spinnerValue);
    }

    private IEditor getFragmentInstance(AppiumDriver driver, WordType type) {
        Map<WordType, Supplier<IEditor>> init = Map.of(
                ADJECTIVE, AdjectivePageObject::new,
                VERB, VerbPageObject::new
        );

        IEditor fragment = init.get(type).get();
        PageFactory.initElements(new AppiumFieldDecorator(driver), fragment);
        return fragment;
    }
}
