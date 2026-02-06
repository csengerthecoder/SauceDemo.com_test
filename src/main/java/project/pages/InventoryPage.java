package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By appLogo = By.className("app_logo");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By filter = By.cssSelector("[data-test='product-sort-container']");
    private final By productNames = By.className("inventory_item_name");

    private final By shoppingCartItemCount = By.cssSelector("[data-test='shopping-cart-badge']");
    public  final By backPackAddButton = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    public  final By bikeLightAddButton = By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']");
    public  final By tShirtAddButton = By.cssSelector("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    public final By jacketAddButton = By.cssSelector("[data-test='add-to-cart-sauce-labs-fleece-jacket']");
    public final By onesieAddButton = By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']");
    public final By redShirtAddButton = By.cssSelector("[data-test='add-to-cart-test.allthethings()-t-shirt-(red)']");
    private final By backPackRemoveButton = By.cssSelector("[data-test='remove-sauce-labs-backpack']");
    private final By bikeLightRemoveButton = By.cssSelector("[data-test='remove-sauce-labs-bike-light']");
    private final By tShirtRemoveButton = By.cssSelector("[data-test='remove-sauce-labs-bolt-t-shirt']");
    private final By jacketRemoveButton = By.cssSelector("[data-test='remove-sauce-labs-fleece-jacket']");
    private final By onesieRemoveButton = By.cssSelector("[data-test='remove-sauce-labs-onesie']");
    private final By redShirtRemoveButton = By.cssSelector("[data-test='remove-test.allthethings()-t-shirt-(red)']");

    private final List<By> addButtons = List.of(
            backPackAddButton,
            bikeLightAddButton,
            tShirtAddButton,
            jacketAddButton,
            onesieAddButton,
            redShirtAddButton
    );

    private final List<By> removeButtons = List.of(
            backPackRemoveButton,
            bikeLightRemoveButton,
            tShirtRemoveButton,
            jacketRemoveButton,
            onesieRemoveButton,
            redShirtRemoveButton
    );


    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLogoLoaded() {
        return !driver.findElements(appLogo).isEmpty()
                && driver.findElement(appLogo).isDisplayed();
    }


    public int getCartCount() {
        List<WebElement> elements = driver.findElements(shoppingCartItemCount);
        if (elements.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(elements.get(0).getText());
    }

    public int addAllItems() {
        int count = 0;
        for (By addButton : addButtons) {
            wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
            count++;
        }
        return count;
    }

    public int removeAllItems() {
        int count = 0;
        for (By removeButton : removeButtons) {
            wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
            count++;
        }
        return count;
    }

    public void setFilterToAlphabeticallyReversed() {
        WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(filter));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name (Z to A)");
    }

    public List<String> getProductNames() {
        List<WebElement> productElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productNames));
        return productElements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isSortedDescending(List<String> list) {
        List<String> sorted = new ArrayList<>(list);
        sorted.sort(Comparator.reverseOrder());
        return sorted.equals(list);
    }
}
