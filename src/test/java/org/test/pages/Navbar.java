package org.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * class for navigation menu present on mostly all pages
 */
public class Navbar extends Page {

    public Navbar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@ngbdropdowntoggle]")
    private WebElement menuButton;
    @FindBy(xpath = "//a[contains(@class,'dropdown-item') and contains(.,'Logout')]")
    private WebElement logoutButton;

    /**
     * clicks element located by xpath :
     * //a[@ngbdropdowntoggle]
     */
    public void clickMenuButton(){
        menuButton.click();
    }
    /**
     * clicks element located by xpath :
     * //a[contains(@class,'dropdown-item') and contains(.,'Logout')]
     */
    public void clickLogoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }
}