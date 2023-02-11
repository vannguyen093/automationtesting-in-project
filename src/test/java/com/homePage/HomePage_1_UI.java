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

public class HomePage_1_UI extends BaseTest {

    private Environment env;
    private WebDriver driver;
    HomePagePO homePage;
    ShopPagePO shopPage;
    ProductPagePO productPage;

    @Parameters({"browser", "evnName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName, @Optional("local") String evnName, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String environmentName = System.getProperty("evn");
        ConfigFactory.setProperty("env", environmentName);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, env.automationTestingInUrl(), evnName, osName, osVersion, ipAddress, portNumber);

        homePage = PageGenerateManager.getHomePage(driver);
        String parentID = homePage.getParentId(driver);
        homePage.closeAllWindowWithoutParent(driver, parentID);
    }

    @Test
    public void HomePage1_01_Three_Sliders_Only(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify the homepage has three sliders only");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        homePage.clickToHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickToBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three sliders only");
        verifyEquals(homePage.getSliderSize(), 3);
    }

    @Test
    public void HomePage1_02_Three_Arrivals_Only(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify the homepage has three arrivals only");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        homePage.clickToHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickToBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);


        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three sliders only");
        verifyEquals(homePage.getSliderSize(), 3);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 04: Verify that the homepage has three arrivals only");
        verifyEquals(homePage.getArrivalSize(), 3);
    }

    @Test
    public void HomePage1_03_Image_Clickable(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify image in arrival should negative");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 01: Click to 'Shop' link at header menu");
        homePage.clickToHeaderLinkByText(driver, "Shop");
        shopPage = PageGenerateManager.getShopPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 02: Click to 'Home' link at breadcrumbs menu");
        shopPage.clickToBreadCrumbsByText(driver, "Home");
        homePage = PageGenerateManager.getHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 03: Verify that the homepage has three arrivals only");
        verifyEquals(homePage.getArrivalSize(), 3);

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 04: Click on the image have title named 'Mastering Javascript'");
        productPage = homePage.clickToImageByTitle("Mastering JavaScript");

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 05: Verify that the system navigate to product page and 'Add to basket' button is displayed");
        verifyTrue(productPage.isAddToBasketBtnDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 06: Click on the 'Add to basket' button");
        productPage.clickToAddToBasketButton();

        ExtentTestManager.getTest().log(Status.INFO, "Home Page - Step 07: Verify that success message is displayed");
        verifyTrue(productPage.isSuccessMessageTextAtProductPageDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
