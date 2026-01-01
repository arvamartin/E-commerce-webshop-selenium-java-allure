package actions;

import framework.core.Browser;
import framework.core.Element;
import framework.core.PropertyReader;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static framework.core.Constants.*;
import static framework.core.Constants.HOME_PAGE_URL;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginAction extends BaseAction<LoginAction> {
    private final LoginPage loginPage;
    private final WebDriver driver;

    public LoginAction() {
        loginPage = new LoginPage();
        driver = Browser.getDriver();
    }

    public void login() {
        navigateTo(LOGIN_URL.getValue());
        enterUsername(STANDARD_USER.getValue());
        enterPassword(STANDARD_PASSWORD.getValue());
        clickOnLoginBtn();
        validateCurrentPage(HOME_PAGE_URL.getValue());
    }


    public LoginAction enterUsername(String username) {
        new Element(loginPage.getUserNameInput())
                .waitForVisible()
                .sendKeys(username);
        return this;
    }

    public LoginAction enterPassword(String password) {
        new Element(loginPage.getPasswordInput())
                .waitForVisible()
                .sendKeys(password);
        return this;
    }

    public LoginAction clickOnLoginBtn() {
        new Element(loginPage.getLoginBtn())
                .waitForVisible()
                .click();
        return this;
    }

    public LoginAction isErrorPopupDisplayedWithMessage(String expectedErrorMessage) {

        new Element(loginPage.getErrorPopup())
                .assertHasTextAndIsVisible(expectedErrorMessage);

        return this;
    }



    public LoginAction verifyTitleText() {
        String expectedTitleText = PropertyReader.getValue("loginUi","loginPage", "titleText");
        String expectedFontSize = PropertyReader.getValue("loginUi","loginPage", "titleTextFontSize");
        String expectedTextColor = PropertyReader.getValue("loginUi","loginPage", "titleTextColor");
        String expectedFontFamily1 = PropertyReader.getValue("loginUi","loginPage", "titleTextFontFamily1");
        String expectedFontFamily2 = PropertyReader.getValue("loginUi","loginPage", "titleTextFontFamily2");

        new Element(loginPage.getTitleElement())
                .assertText(expectedTitleText)
                .assertCssValue("font-size", expectedFontSize)
                .assertCssValue("color", expectedTextColor)
                .assertCssValueContains("font-family", expectedFontFamily1, expectedFontFamily2);

        return this;
    }

    public LoginAction verifyBackgroundColor() {
        String expectedBackgroundColor = PropertyReader.getValue("loginUi","loginPage", "backgroundColor");
        new Element(loginPage.getLoginPageContainer())
                .assertCssValue("background-color", expectedBackgroundColor);
        return this;
    }

    public LoginAction verifyLoginPanel() {
        String expectedColor = PropertyReader.getValue("loginUi","loginPage", "loginPanelColor");
        new Element(loginPage.getLoginPanel())
                .assertCssValue("background-color", expectedColor)
                .shouldBeVisible();

        String expectedUsernamePlaceholder = PropertyReader.getValue("loginUi","loginPage", "usernamePlaceholder");
        new Element(loginPage.getUserNameInput())
                .assertAttribute("placeholder", expectedUsernamePlaceholder);

        String expectedPasswordPlaceholder = PropertyReader.getValue("loginUi","loginPage", "passwordPlaceholder");
        new Element(loginPage.getPasswordInput())
                .assertAttribute("placeholder", expectedPasswordPlaceholder);

        return this;
    }


    public LoginAction verifyLoginButton() {
        String expectedColor = PropertyReader.getValue("loginUi","loginPage", "loginBtnColor");
        new Element(loginPage.getLoginBtn())
                .assertCssValue("background-color", expectedColor)
                .shouldBeVisible();

        String expectedBorderRadius = PropertyReader.getValue("loginUi","loginPage", "loginBtnBorderRadius");
        new Element(loginPage.getLoginBtn())
                .assertCssValue("border-radius", expectedBorderRadius);

        String expectedBtnText = PropertyReader.getValue("loginUi","loginPage", "loginBtnText");
        new Element(loginPage.getLoginBtn())
                .assertAttribute("value", expectedBtnText);

        String expectedBtnTextColor = PropertyReader.getValue("loginUi","loginPage", "loginBtnTextColor");
        new Element(loginPage.getLoginBtn())
                .assertCssValue("color", expectedBtnTextColor);

        return this;
    }


}
