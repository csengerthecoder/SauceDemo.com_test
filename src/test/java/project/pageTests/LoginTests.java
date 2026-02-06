package project.pageTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import project.main.BaseTest;
import project.pages.InventoryPage;

public class LoginTests extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "standard_user, secret_sauce",
            "locked_out_user, secret_sauce",
            "problem_user, secret_sauce",
            "performance_glitch_user, secret_sauce",
            "error_user, secret_sauce",
            "visual_user, secret_sauce"
    })
    void loginTest(String username, String password) {
        loginPage.login(username, password);

        if ("locked_out_user".equals(username)) {
            Assertions.assertTrue(loginPage.isLoginButtonStillVisible());
        } else {
            InventoryPage inventoryPage = new InventoryPage(driver, wait);
            Assertions.assertTrue(inventoryPage.isLogoLoaded());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "standard_user, 123",
            "locked_out_user, 123",
            "problem_user, 123",
            "performance_glitch_user, 123",
            "error_user, 123",
            "visual_user, 123"
    })
    void loginTestWithWrongPassword(String username, String password) {
        loginPage.login(username, password);
        boolean isLoginButtonStillVisible = loginPage.isLoginButtonStillVisible();
        Assertions.assertTrue(isLoginButtonStillVisible);
    }

    @ParameterizedTest
    @CsvSource({
            "standard_user1, secret_sauce",
            "locked_out_user1, secret_sauce",
            "problem_user1, secret_sauce",
            "performance_glitch_user1, secret_sauce",
            "error_user1, secret_sauce",
            "visual_user1, secret_sauce"
    })
    void loginTestWithWrongUsername(String username, String password) {
        loginPage.login(username, password);
        boolean isLoginButtonStillVisible = loginPage.isLoginButtonStillVisible();
        Assertions.assertTrue(isLoginButtonStillVisible);
    }

    @ParameterizedTest
    @CsvSource({
            "standard_user, ''",
            "locked_out_user, ''",
            "problem_user, ''",
            "performance_glitch_user, ''",
            "error_user, ''",
            "visual_user, ''"
    })
    void loginTestWithEmptyPassword(String username, String password) {
        loginPage.login(username, password);
        boolean isLoginButtonStillVisible = loginPage.isLoginButtonStillVisible();
        Assertions.assertTrue(isLoginButtonStillVisible);
    }
}
