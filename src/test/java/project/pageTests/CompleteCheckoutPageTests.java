package project.pageTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.main.BaseTest;
import project.pages.*;

public class CompleteCheckoutPageTests extends BaseTest {
    CompleteCheckoutPage completeCheckoutPage;
    @BeforeEach
    void setup() {
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addAllItems();
        InventoryItemPage itemPage = inventoryPage.clickOnItemName();
        CartPage cartPage = itemPage.goToCartPage();
        CheckoutStepOnePage checkoutStepOnePage = cartPage.goToCheckoutStepOnePage();
        checkoutStepOnePage.fillOutForm("test", "test", "test");
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.continueCheckout();
        completeCheckoutPage = checkoutStepTwoPage.finishCheckout();
    }

    @Test
    void testBackToHomeButton() {
        InventoryPage inventoryPage = completeCheckoutPage.backToProducts();
        boolean isLogoVisible = inventoryPage.isLogoLoaded();
        Assertions.assertTrue(isLogoVisible);
    }
}
