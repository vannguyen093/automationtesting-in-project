package com.homePage;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.HomePagePO;
import pageObjects.PageGenerateManager;
import pageObjects.ProductPagePO;
import pageObjects.ShopPagePO;
import reportConfig.ExtentTestManager;
import ultilities.Environment;

import java.lang.reflect.Method;

public class HomePage_2_Arrivals extends BaseTest {

//    private CustomerData[] userData;
    private Environment env;
    private WebDriver driver;
    HomePagePO homePage;
    ShopPagePO shopPage;
    ProductPagePO productPage;

    @Parameters({"browser", "evnName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeMethod
    public void beforeMethod(@Optional("firefox") String browserName, @Optional("local") String evnName, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String environmentName = System.getProperty("evn");
        ConfigFactory.setProperty("env", environmentName);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, env.automationTestingInUrl(), evnName, osName, osVersion, ipAddress, portNumber);
//        userData = UserDataMapper.getUserData("abc");

        homePage = PageGenerateManager.getHomePage(driver);
        String parentID = homePage.getParentId(driver);
        homePage.closeAllWindowWithoutParent(driver, parentID);
    }

    @Test
    public void HomePage2_01_Arrival_Image_Description(Method method) {
        ExtentTestManager.startTest(method.getName(), "There should be a description regarding that book the user clicked on");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        homePage.clickOnHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickOnBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three arrivals only");
        verifyEquals(homePage.getArrivalSize(), 3);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 04: Click on the image have title named 'Mastering Javascript'");
        productPage = homePage.clickOnImageByTitle("Mastering JavaScript");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Verify that the system navigate to product page and 'Add to basket' button is displayed");
        verifyTrue(productPage.isAddToBasketBtnDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 06: Click to the 'Description' tab");
        productPage.clickOnTheTabByClass("description_tab");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 07: Verify the description title is displayed");
        verifyEquals(productPage.getTabTitleText("Product Description"), "Product Description");
    }

    @Test
    public void HomePage2_02_Arrival_Image_Review(Method method) {
        ExtentTestManager.startTest(method.getName(), "There should be a review regarding that book the user clicked on");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        homePage.clickOnHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickOnBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three arrivals only");
        verifyEquals(homePage.getArrivalSize(), 3);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 04: Click on the image have title named 'Mastering Javascript'");
        productPage = homePage.clickOnImageByTitle("Mastering JavaScript");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Verify that the system navigate to product page and 'Add to basket' button is displayed");
        verifyTrue(productPage.isAddToBasketBtnDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 06: Click to the 'Review' tab");
        productPage.clickOnTheTabByClass("reviews_tab");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 07: Verify the description title is displayed");
        verifyEquals(productPage.getTabTitleText("Reviews"), "Reviews");
    }

    @Test
    public void HomePage2_03_Arrival_Image_AddToBasket(Method method) {
        ExtentTestManager.startTest(method.getName(), "User can add that book to his basket by clicking 'Add to basket' button");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        productPage.clickOnHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickOnBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three arrivals only");
        verifyEquals(homePage.getArrivalSize(), 3);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 04: Click on the image have title named 'Mastering Javascript'");
        productPage = homePage.clickOnImageByTitle("Mastering JavaScript");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Verify that the system navigate to product page and 'Add to basket' button is displayed");
        verifyTrue(productPage.isAddToBasketBtnDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 06: Click on the 'Add to basket' button");
        productPage.clickOnAddToBasketButton();

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 07: Verify that success message is displayed");
        verifyTrue(productPage.isSuccessMessageTextAtProductPageDisplayed());
    }

    @Test
    public void HomePage2_04_Arrival_AddToBasket_More_Books(Method method) {
        ExtentTestManager.startTest(method.getName(), "User try to add more books than the books in stock");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        productPage.clickOnHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickOnBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three arrivals only");
        verifyEquals(homePage.getArrivalSize(), 3);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 04: Click on the image have title named 'Mastering Javascript'");
        productPage = homePage.clickOnImageByTitle("Mastering JavaScript");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Verify that the system navigate to product page and 'Add to basket' button is displayed");
        verifyTrue(productPage.isAddToBasketBtnDisplayed());

        String productStock = productPage.getProductStock();
        String productQuantity = productStock + 1;
        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Input books quantity more than books in stock into quantity text box with value " + productStock);
        productPage.inputToQuantityTextBox(productQuantity);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 06: Click on the 'Add to basket' button");
        productPage.clickOnAddToBasketButton();

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 07: Verify that error message is displayed");
        verifyEquals(productPage.getErrorMessageAtProductPage(), "Vui lòng chọn một giá trị không lớn hơn " + productStock + ".");
    }

    @Test
    public void HomePage2_05_Arrival_AddToBasket_Item(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify the item should display in check out page after clicking 'Add to basket' button");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        productPage.clickOnHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickOnBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three arrivals only");
        verifyEquals(homePage.getArrivalSize(), 3);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 04: Click on the image have title named 'Mastering Javascript'");
        productPage = homePage.clickOnImageByTitle("Mastering JavaScript");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Verify that the system navigate to product page and 'Add to basket' button is displayed");
        verifyTrue(productPage.isAddToBasketBtnDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Input 1 into quantity text box");
        productPage.inputToQuantityTextBox("1");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 06: Click on the 'Add to basket' button");
        productPage.clickOnAddToBasketButton();

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 07: Verify that error message is displayed");
        verifyTrue(productPage.isSuccessMessageTextAtProductPageDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 08: Verify that 1 item is displayed at mini cart");
        verifyEquals(productPage.getProductItemTextAtMiniCart(), "1");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 09: Click on the item at mini cart");
        productPage.clickOnItemAtMiniCart(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 10: Verify that the Basket Totals text is displayed");
        verifyTrue(productPage.isBasketTotalsTextDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        closeBrowserAndDriver();
    }
}
