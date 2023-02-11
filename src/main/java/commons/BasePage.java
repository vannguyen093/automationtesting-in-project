package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BaseUI;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    /**
     * Open page url by inputting the url
     * @param driver
     * @param url
     */
    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    /**
     * Get page title
     * @param driver
     * @return
     */
    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /**
     * Get page ID
     * @param driver
     * @return
     */
    protected String getPageID(WebDriver driver) {
        return driver.getWindowHandle();
    }

    /**
     * Get page url
     * @param driver
     * @return
     */
    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    /**
     * Get page source
     * @param driver
     * @return
     */
    protected String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    /**
     * Back to the previous page
     * @param driver
     */
    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    /**
     * Forward to next page
     * @param driver
     */
    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    /**
     * Refresh the current page
     * @param driver
     */
    protected void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    /**
     * Get all cookies
     * @param driver
     */
    protected Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    /**
     * Set the cookies
     * @param driver
     * @param cookies
     */
    protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    /**
     * Wait a alert present
     * @param driver
     * @return
     */
    protected Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Click to OK button for accept a alert
     * @param driver
     */
    protected void acceptAlert(WebDriver driver) {
        driver.switchTo().alert();
        waitForAlertPresence(driver).accept();
        driver.switchTo().defaultContent();
    }

    /**
     * Click to Cancel button for cancel a alert
     * @param driver
     */
    protected void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    /**
     * Get text of a alert
     * @param driver
     * @return
     */
    protected String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    /**
     * Input value into alert
     * @param driver
     * @param textValue
     */
    protected void sendKeysToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    /**
     * Switch to a window by ID
     * @param driver
     * @param parentID
     */
    protected void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    /**
     * Switch to a window by window title
     * @param driver
     * @param title
     */
    protected void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    /**
     * Close all window except parrent window
     * @param driver
     * @param parentID
     */
    protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    /**
     * Get locator by using "By"
     * @param locatorType
     * @return
     */
    private By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type not supported");
        }
        return by;
    }

    /**
     * Get dynamic element locator
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    private String getDynamicXpath(String locatorType, String... dynamicValues) {
        if (locatorType.startsWith("xpath=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValues);
        }
        return locatorType;
    }

    /**
     * Get a single element locator
     * @param driver
     * @param locatorType
     * @return
     */
    private WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    /**
     * Get a list of elements locator
     * @param driver
     * @param locatorType
     * @return
     */
    protected List<WebElement> getListWebElements(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    /**
     * Get a list of dynamic elements locator
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected List<WebElement> getListWebElements(WebDriver driver, String locatorType, String... dynamicValues) {
        return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
    }

    /**
     * Click to a single element
     * @param driver
     * @param locatorType
     */
    protected void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    /**
     * Click to dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
    }

    /**
     * Clear value in text box before continue input other value
     * @param driver
     * @param locatorType
     */
    protected void clearValueByDeleteKey(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    /**
     * Input value into a single text box
     * @param driver
     * @param locatorType
     * @param textValue
     */
    protected void sendkeysToElement(WebDriver driver, String locatorType, String textValue) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }

    /**
     * Input value into dynamic text boxes
     * @param driver
     * @param locatorType
     * @param textValue
     * @param dynamicValues
     */
    protected void sendkeysToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    /**
     * Select item from single default dropdown list
     * @param driver
     * @param locatorType
     * @param textItem
     */
    protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    /**
     * Select item from dynamic default dropdown list
     * @param driver
     * @param locatorType
     * @param textItem
     * @param dynamicValues
     */
    protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
        select.selectByVisibleText(textItem);
    }

    /**
     * Get the first selected item in single default dropdown list
     * @param driver
     * @param locatorType
     * @return
     */
    protected String getFirstSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Get the first selected item in dynamic default dropdown list
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected String getFirstSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Verify dropdown list is multiple
     * @param driver
     * @param locatorType
     * @return
     */
    protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();
    }

    /**
     * Select item from custom dropdown list
     * @param driver
     * @param parentLocator
     * @param childItemLocator
     * @param expectedItem
     */
    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    /**
     * This is static sleep
     * @param timeInSecond
     */
    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get attribute name of single element
     * @param driver
     * @param locatorType
     * @param attributeName
     * @return
     */
    protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    /**
     * Get attribute name of dynamic elements
     * @param driver
     * @param locatorType
     * @param attributeName
     * @param dynamicValues
     * @return
     */
    protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
    }

    /**
     * Get text of single element
     * @param driver
     * @param locatorType
     * @return
     */
    protected String getElementText(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText();
    }

    /**
     * Get text of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
    }

    /**
     * Get element css value
     * @param driver
     * @param locatorType
     * @param propertyName
     * @return
     */
    protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    /**
     * Convert RBGA to Hex
     * @param rgbaValue
     * @return
     */
    protected String convertRbgaToHex(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    /**
     * Get element size of single element
     * @param driver
     * @param locatorType
     * @return
     */
    protected int getElementSize(WebDriver driver, String locatorType) {
        return getListWebElements(driver, locatorType).size();
    }

    /**
     * Get element size of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
        return getListWebElements(driver, getDynamicXpath(locatorType, dynamicValues)).size();
    }

    /**
     * Select checkbox or radio button of single element
     * @param driver
     * @param locatorType
     */
    protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    /**
     * Select checkbox or radio button of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        if (!element.isSelected()) {
            element.click();
        }
    }

    /**
     * Deselect checkbox or radio button of single element
     * @param driver
     * @param locatorType
     */
    protected void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    /**
     * Deselect checkbox or radio button of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        if (element.isSelected()) {
            element.click();
        }
    }

    /**
     * Verify the element is displayed of single element
     * @param driver
     * @param locatorType
     * @return
     */
    protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
        try {
            return getWebElement(driver, locatorType).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Verify the element is displayed of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        try {
            return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void overrideGlobalTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    /**
     * Verify the element is undisplayed of single element
     * @param driver
     * @param locatorType
     * @return
     */
    protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
        overrideGlobalTimeout(driver, shortTimeOut);
        List<WebElement> elements = getListWebElements(driver, locatorType);
        overrideGlobalTimeout(driver, longTimeOut);

        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify the element is undisplayed of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        overrideGlobalTimeout(driver, shortTimeOut);
        List<WebElement> elements = getListWebElements(driver, getDynamicXpath(locatorType, dynamicValues));
        overrideGlobalTimeout(driver, longTimeOut);

        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify the element is enabled of single element
     * @param driver
     * @param locatorType
     * @return
     */
    protected boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    /**
     * Verify the element is enabled of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
    }

    /**
     * Verify the element is selected of single element
     * @param driver
     * @param locatorType
     * @return
     */
    protected boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    /**
     * Verify the element is selected of dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     * @return
     */
    protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
    }

    /**
     * Swwitch to Frame/ iFrame
     * @param driver
     * @param locatorType
     */
    protected void switchToFrameIframe(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    /**
     * Swwitch to parent window
     * @param driver
     */
    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    /**
     * Hover to a single element
     * @param driver
     * @param locatorType
     */
    protected void hoverMouseToElement(WebDriver driver, String locatorType) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locatorType)).perform();
    }

    /**
     * Switch to dynamic elements
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
    }

    /**
     * Press a key to a single element
     * @param driver
     * @param locatorType
     * @param key
     */
    protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locatorType), key).perform();
    }

    /**
     * Press a key to dynamic elements
     * @param driver
     * @param locatorType
     * @param key
     * @param dynamicValues
     */
    protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
    }

    /**
     * Scroll to bottom page by using Javascript Executor
     * @param driver
     */
    protected void scrollToBottomPageByJS(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    /**
     * Highlight a element by using Javascript Executor
     * @param driver
     * @param locatorType
     */
    protected void highlightElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    /**
     * Click to a single element by using Javascript Executor
     * @param driver
     * @param locatorType
     */
    protected void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    /**
     * Click to dynamic elements by using Javascript Executor
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
    }

    /**
     * Scroll to a element by using Javascript Executor
     * @param driver
     * @param locatorType
     */
    protected void scrollToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    /**
     * Remove a attribute of a element by using Javascript Executor
     * @param driver
     * @param locatorType
     * @param attributeRemove
     */
    protected void removeAttributeInDOMByJS(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }

    /**
     * Verify page loading is ready
     * @param driver
     * @return
     */
    protected boolean areJQueryAndJSLoadedSuccessByJS(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    /**
     * Get a validateion message by using JavaScript Executor
     * @param driver
     * @param locatorType
     * @return
     */
    protected String getElementValidationMessageByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    /**
     * Verify image loaded successfully by using JavaScript Executor
     * @param driver
     * @param locatorType
     * @return
     */
    protected boolean isImageLoadedByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Wait for a single element is visible
     * @param driver
     * @param locatorType
     */
    protected void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    /**
     * Wait for dynamic element is visible
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    /**
     * Wait for all elements are visible
     * @param driver
     * @param locatorType
     */
    protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    /**
     * Wait for all dynamic elements are visible
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    /**
     * Wait for a single element is invisible
     * @param driver
     * @param locatorType
     */
    protected void waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    /**
     * Wait for dynamic elements is invisible
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    /**
     * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
     * @param driver
     * @param locatorType
     */
    protected void waitForElementUndisplayed(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
        overrideGlobalTimeout(driver, shortTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideGlobalTimeout(driver, longTimeOut);
    }

    /**
     * Wait for element undisplayed in DOM or not in DOM by dynamic locator and override implicit timeout
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void waitForElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
        overrideGlobalTimeout(driver, shortTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
        overrideGlobalTimeout(driver, longTimeOut);
    }

    /**
     * Wait for all elements are invisible
     * @param driver
     * @param locatorType
     */
    protected void waitForAllElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locatorType)));
    }

    /**
     * Wait for all dynamic elements are invisible
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, getDynamicXpath(locatorType, dynamicValues))));
    }

    /**
     * Wait for element is clickable
     * @param driver
     * @param locatorType
     */
    protected void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    /**
     * Wait for dynamic elements is clickable
     * @param driver
     * @param locatorType
     * @param dynamicValues
     */
    protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    /**
     * Upload a file or multiple files
     * @param driver
     * @param fileNames
     */
    protected void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_FILE;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, GlobalConstants.UPLOAD_FILE).sendKeys(fullFileName);
    }

    /**
     * Click to menu link at header menu by inputting menu text
     * @param driver
     * @param menuText
     */
    public void clickOnHeaderLinkByText(WebDriver driver, String menuText) {
        waitForElementVisible(driver, BaseUI.HEADER_MENU_LINK_TEXT, menuText);
        clickToElement(driver, BaseUI.HEADER_MENU_LINK_TEXT, menuText);
    }

    /**
     * Get home page ID
     * @param driver
     * @return
     */
    public String getParentId(WebDriver driver){
        return getPageID(driver);
    }

    /**
     * Close the extension page and switch to home page
     * @param driver
     * @param parentID
     */
    public void closeAllWindowWithoutParent(WebDriver driver, String parentID){
        closeAllWindowsWithoutParent(driver, getParentId(driver));
    }

    /**
     * Click on the mini cart at header menu to navigate to check out page
     * @param driver
     */
    public void clickOnItemAtMiniCart(WebDriver driver) {
        waitForElementClickable(driver, BaseUI.MINI_CART);
        clickToElement(driver, BaseUI.MINI_CART);
    }
}
