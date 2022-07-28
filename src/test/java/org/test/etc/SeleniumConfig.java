package org.test.etc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Config class
 */
public class SeleniumConfig {
    /** WebDriver object */
    private WebDriver driver;
    /** @return driver */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Initialize the driver object and sets options
     * @param browserName - name of the using browser :
     *                    Google Chrome   => Chrome;
     *                    Yandex          => Yandex;
     *                    Microsoft Edge  => Edge;
     *                    Opera           => Opera;
     *                    Mozilla Firefox => Firefox.
     * @throws NullPointerException when browserName is null.
     */
    private void initDriver( final String browserName) throws NullPointerException {
        if( browserName == null) {
            throw new NullPointerException("cannot use null as browserName");
        }
        String browserPath = ConfProperties.getProperty(browserName.toLowerCase());
        switch (browserName) {
            case "Chrome" -> {
                System.setProperty( "webdriver.chrome.driver",
                        ConfProperties.getProperty("chromedriver"));

                ChromeOptions options = new ChromeOptions();
                options.setBinary(browserPath)
                        .addArguments("--no-sandbox");
                this.driver = new ChromeDriver(options);
            }
            case "Yandex" -> {
                System.setProperty( "webdriver.chrome.driver",
                        ConfProperties.getProperty("yandexdriver"));

                ChromeOptions options = new ChromeOptions();
                options.setBinary(browserPath)
                        .addArguments("--no-sandbox");
                this.driver = new ChromeDriver(options);
            }
            case "Firefox" -> {
                System.setProperty( "webdriver.gecko.driver",
                        ConfProperties.getProperty("firefoxdriver"));

                FirefoxOptions options = new FirefoxOptions();
                options.setBinary(browserPath)
                        .addArguments("--no-sandbox");
                this.driver = new FirefoxDriver(options);
            }
            case "Edge" -> {
                System.setProperty( "webdriver.edge.driver",
                        ConfProperties.getProperty("edgedriver"));

                EdgeOptions options = new EdgeOptions();
                options.setBinary(browserPath)
                        .addArguments("--no-sandbox");
                this.driver = new EdgeDriver(options);
            }
            case "Opera" -> {
                System.setProperty( "webdriver.chrome.driver",
                        ConfProperties.getProperty("operadriver"));

                ChromeOptions options = new ChromeOptions();
                options.setBinary(browserPath)
                        .addArguments("--no-sandbox")
                        .setExperimentalOption("w3c", true);
                this.driver = new ChromeDriver(options);
            }
            default -> driver = null;
        }
    }

    /**
     * Sets driver options,
     * inits driver,
     * sets window size to maximum,
     * sets timeouts.
     */
    public SeleniumConfig(final String browserName) {
        initDriver(browserName);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(20) );
        driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(10) );
        driver.manage().timeouts().scriptTimeout( Duration.ofSeconds(5) );
    }
}
