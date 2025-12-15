package actions;

import framework.core.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginAction {
    private final LoginPage loginPage;
    private final WebDriver driver;

    public LoginAction() {
        loginPage = new LoginPage();
        driver = Browser.getDriver();
    }


    public LoginAction navigateTo(String url) {
        driver.get(url);
        return this;
    }

    public LoginAction enterUsername(String username) {
        loginPage.getUserNameInput().sendKeys(username);
        return this;
    }

    public LoginAction enterPassword(String password) {
        loginPage.getPasswordInput().sendKeys(password);
        return this;
    }

    public LoginAction clickOnLoginBtn() {
        loginPage.getLoginBtn().click();
        return this;
    }

    public LoginAction isErrorPopupDisplayedWithMessage(String expectedErrorMessage) {
        WebElement errorPopup = loginPage.getErrorPopup();

        assertThat(errorPopup.isDisplayed(), is(true));
        assertThat(expectedErrorMessage, equalTo(errorPopup.getText()));

        return this;
    }


    public void validateCurrentPage(String expectedUrl){
        String actualUrl = driver.getCurrentUrl();

        assertThat(expectedUrl, equalTo(actualUrl));
    }

}
