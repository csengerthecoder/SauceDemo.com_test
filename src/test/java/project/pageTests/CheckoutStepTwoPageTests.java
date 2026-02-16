package project.pageTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.main.BaseTest;
import project.pages.*;

public class CheckoutStepTwoPageTests extends BaseTest {
    CheckoutStepTwoPage checkoutStepTwoPage;

    @BeforeEach
    void setup() {
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addAllItems();
        InventoryItemPage itemPage = inventoryPage.clickOnItemName();
        CartPage cartPage = itemPage.goToCartPage();
        CheckoutStepOnePage checkoutStepOnePage = cartPage.goToCheckoutStepOnePage();
        checkoutStepOnePage.fillOutForm("test", "test", "test");
        checkoutStepTwoPage = checkoutStepOnePage.continueCheckout();
    }

    @Test
    void isTotalPriceCalculatedCorrectly() {
        boolean isPriceCalculatedCorrectly = checkoutStepTwoPage.isPriceAddedCorrectly();
        Assertions.assertTrue(isPriceCalculatedCorrectly);
    }

    @Test
    void testFinishButton() {
        CompleteCheckoutPage completePage = checkoutStepTwoPage.finishCheckout();
        boolean isBackButtonVisible = completePage.isBackToHomeButtonDisplayed();
        Assertions.assertTrue(isBackButtonVisible);
    }
}
