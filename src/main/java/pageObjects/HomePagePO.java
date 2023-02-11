package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePagePO extends BasePage {
    WebDriver driver;

    public HomePagePO(WebDriver driver) {
        this.driver = driver;
    }

    public int getSliderSize() {
        waitForElementVisible(driver, HomePageUI.HEADER_SLIDER_IMAGE);
        return getElementSize(driver, HomePageUI.HEADER_SLIDER_IMAGE);
    }

    public int getArrivalSize() {
        waitForElementVisible(driver, HomePageUI.ARRIVAL_TITLE);
        return getElementSize(driver, HomePageUI.ARRIVAL_TITLE);
    }

    public ProductPagePO clickToImageByTitle(String imageText) {
        waitForElementClickable(driver, HomePageUI.ARRIVAL_IMAGE_TEXT, imageText);
        clickToElement(driver, HomePageUI.ARRIVAL_IMAGE_TEXT, imageText);
        return PageGenerateManager.getProductPage(driver);
    }
}
