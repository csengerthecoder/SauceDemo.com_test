package project.pageTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.main.BaseTest;
import project.pages.*;

public class CheckoutStepOnePageTests extends BaseTest {
    private CheckoutStepOnePage  checkoutStepOnePage;

    @BeforeEach
    void setup() {
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addAllItems();
        InventoryItemPage itemPage = inventoryPage.clickOnItemName();
        CartPage cartPage = itemPage.goToCartPage();
        checkoutStepOnePage = cartPage.goToCheckoutStepOnePage();
    }

    @Test
    void checkoutCorrectly() {
        checkoutStepOnePage.fillOutForm("Sophie", "Example", "5000");
        CheckoutStepTwoPage checkoutTwo = checkoutStepOnePage.continueCheckout();
        Assertions.assertTrue(checkoutTwo.isLoginButtonVisible());
    }

    @Test
    void checkoutWithoutCredentials() {
        checkoutStepOnePage.fillOutForm("", "", "");
        CheckoutStepTwoPage checkoutTwo = checkoutStepOnePage.continueCheckout();
        Assertions.assertTrue(checkoutStepOnePage.isContinueButtonVisible());
    }

    @Test
    void goBackToCart() {
        CartPage cartPage = checkoutStepOnePage.cancelCheckout();
        cartPage.isCheckoutButtonDisplayed();
    }
}
