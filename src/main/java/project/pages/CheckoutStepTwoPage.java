package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutStepTwoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By finishButton = By.id("finish");
    private final By itemPrices = By.className("inventory_item_price");
    private final By subTotalPrice = By.cssSelector("[data-test='subtotal-label']");

    public CheckoutStepTwoPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLoginButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton)).isDisplayed();
    }

    private double calculateTotalPrice() {
        List<WebElement> prices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemPrices));
        double totalPrice = 0;
        for (WebElement price : prices) {
            String text = price.getText();
            String number = text.replaceAll("[^0-9.]", "");
            totalPrice += Double.parseDouble(number);
        }
        return totalPrice;
    }

    public boolean isPriceAddedCorrectly() {
        double totalPrice = calculateTotalPrice();
        WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(subTotalPrice));
        String subTotalPriceText = price.getText();
        String number = subTotalPriceText.replaceAll("[^0-9.]", "");
        return totalPrice == Double.parseDouble(number);
    }

    public CompleteCheckoutPage finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
        return new CompleteCheckoutPage(driver, wait);
    }
}
