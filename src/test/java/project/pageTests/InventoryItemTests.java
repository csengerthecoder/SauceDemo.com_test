package project.pageTests;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.main.BaseTest;
import project.pages.CartPage;
import project.pages.InventoryItemPage;
import project.pages.InventoryPage;

public class InventoryItemTests extends BaseTest {
    private InventoryItemPage itemPage;

    @BeforeEach
    void setup() {
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        itemPage = inventoryPage.clickOnItemName();
    }

    @Test
    void testAddToCart() {
        int cartCount = itemPage.getCartCount();
        itemPage.addToCart();
        Assertions.assertEquals(cartCount + 1, itemPage.getCartCount());
    }

    @Test
    void testRemoveFromCart() {
        itemPage.addToCart();
        int cartCount = itemPage.getCartCount();
        itemPage.removeFromCart();
        Assertions.assertEquals(cartCount - 1, itemPage.getCartCount());
    }

    @Test
    void testGoBackToInventoryPage() {
        InventoryPage inventoryPage = itemPage.goBackToInventoryPage();
        Assertions.assertTrue(inventoryPage.isLogoLoaded());
    }

    @Test
    void testGoToCartPage() {
        CartPage cartPage = itemPage.goToCartPage();
        Assertions.assertTrue(cartPage.isCheckoutButtonDisplayed());
    }
}
