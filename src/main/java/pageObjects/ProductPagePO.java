package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ProductPageUI;

public class ProductPagePO extends BasePage {
    WebDriver driver;

    public ProductPagePO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAddToBasketBtnDisplayed() {
        waitForElementVisible(driver, ProductPageUI.ADD_TO_BASKET_BUTTON);
        return isElementDisplayed(driver, ProductPageUI.ADD_TO_BASKET_BUTTON);
    }

    public void clickToAddToBasketButton() {
        waitForElementClickable(driver, ProductPageUI.ADD_TO_BASKET_BUTTON);
        clickToElement(driver, ProductPageUI.ADD_TO_BASKET_BUTTON);
    }

    public boolean isSuccessMessageTextAtProductPageDisplayed() {
        waitForElementVisible(driver, ProductPageUI.SUCCESS_MESSAGE_TEXT);
        return isElementDisplayed(driver, ProductPageUI.SUCCESS_MESSAGE_TEXT);
    }

    public void clickToTheTabByClass(String tabClass) {
        waitForElementClickable(driver, ProductPageUI.TAB_BY_CLASS, tabClass);
        clickToElement(driver, ProductPageUI.TAB_BY_CLASS, tabClass);
    }

    public String getTabTitleText(String descriptionTitleText) {
        waitForElementVisible(driver, ProductPageUI.TAB_TITLE_TEXT, descriptionTitleText);
        return getElementText(driver, ProductPageUI.TAB_TITLE_TEXT, descriptionTitleText);
    }

    public String getProductStock() {
        waitForElementVisible(driver, ProductPageUI.PRODUCT_STOCK_TEXT);
        String[] productStock = getElementText(driver, ProductPageUI.PRODUCT_STOCK_TEXT).split(" ");
        return productStock[0];
    }

    public void inputToQuantityTextBox(String productQuantityNumber) {
        waitForElementVisible(driver, ProductPageUI.PRODUCT_QUANTITY_TEXTBOX);
        sendkeysToElement(driver, ProductPageUI.PRODUCT_QUANTITY_TEXTBOX, productQuantityNumber);
    }

    public String getErrorMessageAtProductPage() {
        waitForElementVisible(driver, ProductPageUI.PRODUCT_QUANTITY_TEXTBOX);
        return getElementAttribute(driver, ProductPageUI.PRODUCT_QUANTITY_TEXTBOX, "validationMessage");
    }
}
