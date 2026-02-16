package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompleteCheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By backToHomeButton = By.id("back-to-products");

    public CompleteCheckoutPage(WebDriver driver,  WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isBackToHomeButtonDisplayed() {
        return wait.until(ExpectedConditions.elementToBeClickable(backToHomeButton)).isDisplayed();
    }

    public InventoryPage backToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(backToHomeButton)).click();
        return new InventoryPage(driver, wait);
    }
}
