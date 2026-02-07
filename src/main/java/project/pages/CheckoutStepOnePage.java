package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepOnePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By cancelButton = By.cssSelector("[data-test='cancel']");
    private final By firstName = By.cssSelector("[data-test='firstName']");
    private final By lastName = By.cssSelector("[data-test='lastName']");
    private final By zipCode =  By.cssSelector("[data-test='postalCode']");

    public CheckoutStepOnePage(WebDriver driver,  WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isContinueButtonVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        return driver.findElement(continueButton).isDisplayed();
    }


}
