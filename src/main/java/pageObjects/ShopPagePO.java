package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ShopPageUI;

public class ShopPagePO extends BasePage {
    WebDriver driver;

    public ShopPagePO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToBreadCrumbsByText(WebDriver driver, String breadCrumbsText) {
        waitForElementVisible(driver, ShopPageUI.BREAD_CRUMBS_LINK_TEXT, breadCrumbsText);
        clickToElement(driver, ShopPageUI.BREAD_CRUMBS_LINK_TEXT, breadCrumbsText);
    }
}
