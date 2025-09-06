package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    private LoginPage login;

    @BeforeMethod(alwaysRun = true)
    public void loadPage() {
        login = new LoginPage(driver);
        Assert.assertTrue(login.isLoaded(), "Login page failed to load");
    }

    @Test(description = "Attempt login with blank fields and verify login button behavior")
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        boolean enabled = login.isLoginButtonEnabled();
        if (enabled) {
            login.clickLogin();
            String err = login.getErrorTextIfAny();
            Assert.assertFalse(err.isEmpty(), "Expected validation/error message when clicking with empty fields");
        } else {
            Assert.assertFalse(enabled, "Login button should be disabled when fields are empty");
        }
    }

    @Test(description = "Validate password masking/unmasking toggle")
    public void testPasswordMaskedButton() {
        // default should be masked
        Assert.assertEquals(login.getPasswordInputType(), "password", "Password should be masked by default");

        // toggle to unmask
        login.togglePasswordVisibility();
        Assert.assertEquals(login.getPasswordInputType(), "text", "Password should be visible after toggling");

        // toggle back to mask
        login.togglePasswordVisibility();
        Assert.assertEquals(login.getPasswordInputType(), "password", "Password should be masked again after toggling back");
    }

    @Test(dataProvider = "invalidCredentials", description = "Enter invalid credentials and verify error message")
    public void testInvalidLoginShowErrorMsg(String user, String pass) {
        login.enterUserId(user)
                .enterPassword(pass)
                .clickLogin();

        String error = login.getErrorTextIfAny();
        Assert.assertTrue(error != null && !error.trim().isEmpty(), "Expected an error message for invalid login");
        Reporter.log("Captured error message: " + error, true);
    }


    @DataProvider
    public Object[][] invalidCredentials() {
        return new Object[][] {
                {"fakeuser@example.com", "wrongpassword"},
                {"", "somepass"},
                {"user@example.com", ""},
                {"   ", "   "}
        };
    }

    @Test(description = "Validate presence of login page UI elements")
    public void testUIElementsPresent() {
        Assert.assertTrue(login.isUserIdPresent(), "User ID field missing");
        Assert.assertTrue(login.isPasswordPresent(), "Password field missing");
        Assert.assertTrue(login.isLoginButtonPresent(), "Login button missing");
        Assert.assertTrue(login.isPasswordTogglePresent(), "Password visibility toggle missing");
    }

//    @Test(description = "Login with valid credentials should succeed")
//    public void testValidLogin() {
//        login.enterUserId(System.getProperty("validUser", "valid@example.com")) // replace email with a valid email
//                .enterPassword(System.getProperty("validPass", "password123")) // replace password with a valid password
//                .clickLogin();
//
//        boolean dashboardLoaded = driver.getCurrentUrl().contains("/dashboard");
//        Assert.assertTrue(dashboardLoaded, "Dashboard should load after valid login");
//    }
}
