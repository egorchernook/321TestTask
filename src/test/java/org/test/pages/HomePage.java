package org.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

// page_url = https://suite8demo.suiteondemand.com/#/home
public class HomePage extends Page {

    public HomePage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(@class, 'global-user-name ng-tns-c302-1')][not(normalize-space(.)='')]")
    private WebElement userName;
    @FindBy(xpath = "//button[contains(@type,'button') and contains(.,'New')]")
    private WebElement newButton;
    @FindBy(xpath = "//div[contains(@class,'action-new dropdown')]//a[contains(.,'Leads') and @href]")
    private WebElement leadsButtonFromNewMenu;

    /**
     * text from element that contains username and located by xpath :
     * //span[contains(@class, 'global-user-name ng-tns-c302-1')][not(normalize-space(.)='')]
     * @return name of created lead
     */
    public String getUserName(){
        return userName.getText();
    }
    /**
     * clicks element located by xpath :
     * //button[contains(@type,'button') and contains(.,'New')]
     */
    public void clickNewButton(){
        wait.until(ExpectedConditions.elementToBeClickable(newButton));
        final var actions = new Actions(driver);
        actions.moveToElement(newButton).click().perform();
    }
    /**
     * clicks element located by xpath :
     * //div[contains(@class,'action-new dropdown')]//a[contains(.,'Leads') and @href]
     */
    public void clickNewLeadsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(leadsButtonFromNewMenu));
        leadsButtonFromNewMenu.click();
    }
}