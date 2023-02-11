package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGenerateManager {
    public static HomePagePO getHomePage(WebDriver driver){
        return new HomePagePO(driver);
    }
    public static ShopPagePO getShopPage(WebDriver driver){
        return new ShopPagePO(driver);
    }
    public static ProductPagePO getProductPage(WebDriver driver){
        return new ProductPagePO(driver);
    }
}
