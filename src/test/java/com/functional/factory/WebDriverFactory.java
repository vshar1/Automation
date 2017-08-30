package com.functional.factory;

import com.functional.browsers.Browsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.util.page.ResourceLoader.getProperty;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WebDriverFactory {
    private static final String BROWSER_PROP_KEY = "browser";
    private static final String CHROME_DRIVER_PROP_KEY = "chrome.driver.path";
    private static final String DRIVER_TIMEOUT_PROP_KEY = "driver.timeout";

    public static WebDriver create() {
        WebDriver webDriver = null;
        String browserProperty = getProperty(BROWSER_PROP_KEY);
        Browsers browser = Browsers.browserForName(browserProperty);

        try {
            switch (browser) {
                case CHROME:
                    String chromeDriverPath = getProperty(CHROME_DRIVER_PROP_KEY);
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    webDriver = new ChromeDriver(DesiredCapabilities.chrome());
                    break;

                case FIREFOX:
                    webDriver = new FirefoxDriver(DesiredCapabilities.firefox());
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
           setUp(webDriver);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return webDriver;
    }

    private static void setUp(WebDriver webDriver) {
        webDriver.manage().window().maximize();
        setDriverTimeoutParameters(webDriver);
    }

    private static void setDriverTimeoutParameters(WebDriver webDriver) {
        long timeout = Long.parseLong(getProperty(DRIVER_TIMEOUT_PROP_KEY));
        Timeouts timeouts = webDriver.manage().timeouts();
        timeouts.implicitlyWait(timeout, SECONDS);
        timeouts.setScriptTimeout(timeout, SECONDS);
    }
}
