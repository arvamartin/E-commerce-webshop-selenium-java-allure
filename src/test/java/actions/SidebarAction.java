package actions;

import framework.core.Browser;
import framework.core.Element;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.components.Sidebar;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SidebarAction extends BaseAction<SidebarAction> {

    private Sidebar sidebar;
    private final WebDriver driver;


    public SidebarAction() {
        this.sidebar = new Sidebar();
        driver = Browser.getDriver();
    }

    public SidebarAction navigateTo(String url) {
        sidebar.navigateTo(url);
        return this;
    }

    public SidebarAction openSidebar() {
        new Element(sidebar.getMenuBtn())
                .waitForClickable().click();

        new Element(sidebar.getSidebarPanel())
                .shouldBeVisible();

        return this;
    }


    public SidebarAction validatePresenceOfSidebarItems() {
        for (WebElement element : sidebar.getSidebarElements()) {
            try {
                new Element(element).waitForVisible();
            } catch (TimeoutException e) {
                throw new AssertionError(
                        "Sidebar element not visible: " + element.getText(), e
                );
            }
        }
        return this;
    }

    public SidebarAction sidebarMenuIsNotDisplayed() {
        WebElement sidebarPanel = sidebar.getSidebarPanel();

        new Element(sidebarPanel)
                .waitForInvisible()
                .shouldNotBeVisible();
        return this;
    }


    public SidebarAction logout() {
        new Element(sidebar.getLogoutBtn()).waitForClickable().click();
        return this;
    }

    public SidebarAction clickOnAboutBtn() {
        new Element(sidebar.getAboutBtn()).waitForClickable().click();
        return this;
    }

    public SidebarAction clickOnAllItemsBtn(){
        new Element(sidebar.getAllItemsBtn()).waitForClickable().click();
        return this;
    }

    public SidebarAction clickOnCloseCross() {
            WebElement closeBtn = sidebar.getCloseBtn();

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", closeBtn);

            return this;
        }
    }