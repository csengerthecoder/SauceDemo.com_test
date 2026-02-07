package project.pageTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.main.BaseTest;
import project.pages.CartPage;
import project.pages.CheckoutStepOnePage;
import project.pages.InventoryItemPage;
import project.pages.InventoryPage;

public class CartPageTests extends BaseTest {
    private CartPage cartPage;

    @BeforeEach
    void setup() {
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addAllItems();
        InventoryItemPage itemPage = inventoryPage.clickOnItemName();
        cartPage = itemPage.goToCartPage();
    }

    @Test
    void testRemoveItem() {
        int beforeCartItemCount = cartPage.getCartCount();
        cartPage.removeItem();
        int afterCartCount = cartPage.getCartCount();
        Assertions.assertEquals(beforeCartItemCount - 1, afterCartCount);
    }

    @Test
    void testGoToItemPage() {
        InventoryItemPage inventoryItemPage = cartPage.goToItemPage();
        Assertions.assertTrue(inventoryItemPage.isBackToProductsButtonVisible());
    }

    @Test
    void testGoBackToInventoryPage() {
        InventoryPage mainInventoryPage =  cartPage.goBackToInventoryPage();
        Assertions.assertTrue(mainInventoryPage.isLogoLoaded());
    }

    @Test
    void testoGoToCheckoutStepOnePage() {
        CheckoutStepOnePage checkoutOnePage = cartPage.goToCheckoutStepOnePage();
        Assertions.assertTrue(checkoutOnePage.isContinueButtonVisible());
    }
}
