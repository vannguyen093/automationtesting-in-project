package factoryEnvironment;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class LocalFactory {
    private WebDriver driver;
    private String browserName;

    public LocalFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver createDriver() {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\browserLogs\\FirefoxLog.log");
                FirefoxProfile profile = new FirefoxProfile();
                File adBlock = new File(GlobalConstants.PROJECT_PATH + "\\browserExtension\\adblock_for_firefox-5.3.2.xpi");
                profile.addExtension(adBlock);
                profile.setPreference("intl.locale.requested","en-GB");
                profile.setPreference("intl.accept_languages", "en-GB");
                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(profile);
                driver = new FirefoxDriver(options);
                break;
            case H_FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions hFOptions = new FirefoxOptions();
                hFOptions.addArguments("--headless");
                hFOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(hFOptions);
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.args", "--disable-logging");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                File file = new File(GlobalConstants.PROJECT_PATH + "\\browserExtension\\adblock_chrome.crx");
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addExtensions(file);
                driver = new ChromeDriver(cOptions);
                break;
            case H_CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionChrome = new ChromeOptions();
                optionChrome.addArguments("--headless");
                optionChrome.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(optionChrome);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browserName);
        }
        return driver;
    }
}
