package project.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.pages.LoginPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        String gridUrl = System.getProperty("gridUrl");

        ChromeOptions options = new ChromeOptions();

        // Fresh Chrome profile every run (prevents Google password/security popups)
        try {
            Path tempProfile = Files.createTempDirectory("selenium-profile-");
            options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temp Chrome profile", e);
        }

        // Disable password manager + leak detection
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // Extra safety flags to avoid browser UI interruptions
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
        options.addArguments("--disable-extensions");

        String osName = System.getProperty("os.name", "").toLowerCase();
        String ci = System.getenv("CI");

        if (osName.contains("linux") || (ci != null && ci.equalsIgnoreCase("true"))) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        if (gridUrl != null && !gridUrl.isBlank()) {
            try {
                URL url = new URL(gridUrl);
                driver = new RemoteWebDriver(url, options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid gridUrl: " + gridUrl, e);
            }
        } else {
            driver = new ChromeDriver(options);
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        loginPage = new LoginPage(driver, wait);

        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
