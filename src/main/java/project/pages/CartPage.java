package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By goToCheckoutButton = By.cssSelector("[data-test='checkout']");


    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isCheckoutButtonDisplayed() {
        return driver.findElement(goToCheckoutButton).isDisplayed();
    }
}
