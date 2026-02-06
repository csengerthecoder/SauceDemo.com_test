package project.pageTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.main.BaseTest;
import project.pages.InventoryPage;
import project.pages.LoginPage;

import java.util.List;


public class InventoryPageTest extends BaseTest {

    private InventoryPage inventory;

    @BeforeEach
    void setupPage() {
        inventory = loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    void addAllItemsTest() {
        int itemAddedCount = inventory.addAllItems();
        int afterCartCount = inventory.getCartCount();
        Assertions.assertTrue(itemAddedCount == afterCartCount);
    }

    @Test
    void removeAllItemsTest() {
        inventory.addAllItems();
        int beforeRemoveCount = inventory.getCartCount();
        int itemRemovedCount = inventory.removeAllItems();
        Assertions.assertTrue(itemRemovedCount == beforeRemoveCount);
    }

    @Test
    void testReversedAlphabeticalOrder() {
        inventory.setFilterToAlphabeticallyReversed();
        List<String> productNames = inventory.getProductNames();
        Assertions.assertTrue(inventory.isSortedDescending(productNames));
    }

    @Test
    void testLogout() {
        LoginPage loginPage = inventory.logout();
        Assertions.assertTrue(loginPage.isLoginButtonVisible());
    }
}
