package org.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base page abstract class
 */
public abstract class Page {
    /** WebDriver object */
    protected final WebDriver driver;
    /** WebDriverWait object */
    protected final WebDriverWait wait;
    /** @return driver */
    public final WebDriver getDriver() {
        return this.driver;
    }
    /** @param driver - WebDriver object used in tests */
    protected Page(final WebDriver driver ) {
        this.driver = driver;
        this.wait = new WebDriverWait( driver, Duration.ofSeconds(5), Duration.ofMillis(100));
    }
}
