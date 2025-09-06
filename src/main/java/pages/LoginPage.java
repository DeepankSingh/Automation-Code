package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators (constants, since they don’t change at runtime)
    private static final By USER_ID_INPUT = By.id("formEmail");
    private static final By PASSWORD_INPUT = By.id("formPassword");
    private static final By LOGIN_BUTTON = By.cssSelector("button.login-button");
    private static final By PASSWORD_TOGGLE = By.cssSelector("img.passowrd-visible");
    private static final By ERROR_MESSAGE = By.cssSelector(".invalid-credential-div .normal-text, p.normal-text");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Load/verify login page
    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON)).isDisplayed();
    }

    // Actions (Fluent API style)
    public LoginPage enterUserId(String user) {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(USER_ID_INPUT));
        e.clear();
        e.sendKeys(user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_INPUT));
        e.clear();
        e.sendKeys(pass);
        return this;
    }

    public LoginPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
        return this;
    }

    public LoginPage togglePasswordVisibility() {
        try {
            WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_TOGGLE));
            toggle.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Password visibility toggle not found or not clickable", e);
        }
        return this;
    }

    // Validations
    public boolean isLoginButtonEnabled() {
        return driver.findElement(LOGIN_BUTTON).isEnabled();
    }

    public boolean isUserIdPresent() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(USER_ID_INPUT));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPasswordPresent() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PASSWORD_INPUT));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLoginButtonPresent() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(LOGIN_BUTTON));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPasswordTogglePresent() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PASSWORD_TOGGLE));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Helpers
    public String getErrorTextIfAny() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).getText();
        } catch (TimeoutException e) {
            return ""; // safer than null
        }
    }

    public String getPasswordInputType() {
        return driver.findElement(PASSWORD_INPUT).getAttribute("type");
    }

    // Navigation helper
    public LoginPage open(String url) {
        driver.get(url);
        isLoaded();
        return this;
    }
}
