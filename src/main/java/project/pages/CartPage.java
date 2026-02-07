package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By goToCheckoutButton = By.cssSelector("[data-test='checkout']");
    private final By backButton = By.cssSelector("[data-test='continue-shopping']");
    private final By removeItemButton = By.className("cart_button");
    private final By itemName = By.className("inventory_item_name");
    private final By shoppingCartCount = By.cssSelector("[data-test='shopping-cart-badge']");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isCheckoutButtonDisplayed() {
        return driver.findElement(goToCheckoutButton).isDisplayed();
    }

    public InventoryPage goBackToInventoryPage() {
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
        return new InventoryPage(driver, wait);
    }

    public InventoryItemPage goToItemPage() {
        List<WebElement> itemNames = driver.findElements(itemName);
        wait.until(ExpectedConditions.elementToBeClickable(itemNames.get(0))).click();
        return new InventoryItemPage(driver, wait);
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(driver.findElement(shoppingCartCount).getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public void removeItem() {
        List<WebElement> removeItemButtons = driver.findElements(removeItemButton);
        wait.until(ExpectedConditions.elementToBeClickable(removeItemButtons.get(0))).click();
    }

    public CheckoutStepOnePage goToCheckoutStepOnePage() {
        wait.until(ExpectedConditions.elementToBeClickable(goToCheckoutButton)).click();
        return new CheckoutStepOnePage(driver, wait);
    }
}
