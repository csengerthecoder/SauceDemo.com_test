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
    private final By firstNameInput = By.cssSelector("[data-test='firstName']");
    private final By lastNameInput = By.cssSelector("[data-test='lastName']");
    private final By zipCodeInput =  By.cssSelector("[data-test='postalCode']");

    public CheckoutStepOnePage(WebDriver driver,  WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isContinueButtonVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        return driver.findElement(continueButton).isDisplayed();
    }

    public void fillOutForm(String firstName, String lastName, String zipCode) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.elementToBeClickable(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.elementToBeClickable(zipCodeInput)).sendKeys(zipCode);
    }

    public CheckoutStepTwoPage continueCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        return new CheckoutStepTwoPage(driver, wait);
    }
    public CartPage cancelCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
        return new  CartPage(driver, wait);
    }
}
