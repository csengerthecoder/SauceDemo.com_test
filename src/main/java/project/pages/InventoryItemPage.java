package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryItemPage {
    private WebDriver  driver;
    private WebDriverWait wait;
    private final By addToCartButton = By.cssSelector("[data-test='add-to-cart']");
    private final By shoppingCartCount = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By shoppingCartButton = By.cssSelector("[data-test='shopping-cart-link']");
    private final By backToProductsButton = By.cssSelector("[data-test='back-to-products']");
    private final By removeItemButton = By.id("remove");

    public InventoryItemPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public InventoryPage goBackToInventoryPage() {
        wait.until(ExpectedConditions.elementToBeClickable(backToProductsButton)).click();
        return new InventoryPage(driver, wait);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void removeFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeItemButton)).click();
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(driver.findElement(shoppingCartCount).getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public boolean isBackToProductsButtonVisible() {
        return driver.findElement(backToProductsButton).isDisplayed();
    }

    public CartPage goToCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButton)).click();
        return new CartPage(driver, wait);
    }
}
