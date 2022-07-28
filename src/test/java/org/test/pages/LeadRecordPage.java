package org.test.pages;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://suite8demo.suiteondemand.com/#/leads/record/*
public class LeadRecordPage extends Page {

    public LeadRecordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'label-container') and starts-with(.,'NAME')]/..//scrm-field")
    private WebElement nameField;
    @FindBy(xpath = "//button[contains(normalize-space(.),'Actions') and contains(@class,'settings-button')]")
    private WebElement actionsButton;
    @FindBy(xpath = "//a[contains(normalize-space(.),'Convert Lead')]")
    private WebElement convertLeadButton;

    /**
     * text from name field located by xpath :
     * //div[contains(@class,'label-container') and starts-with(.,'NAME')]/..//scrm-field
     * @return name of created lead
     */
    public String getName(){
        return nameField.getText();
    }
    /**
     * clicks element located by xpath :
     * //button[contains(normalize-space(.),'Actions') and contains(@class,'settings-button')]
     */
    public void clickActionsButton(){

        wait.until(ExpectedConditions
                .presenceOfElementLocated( By
                .xpath("//button[contains(normalize-space(.),'Actions') and contains(@class,'settings-button')]")
                ));
        new Actions(driver).moveToElement(actionsButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(actionsButton));
        actionsButton.click();
    }
    /**
     * clicks element located by xpath :
     * //a[contains(normalize-space(.),'Convert Lead')]
     */
    public void clickConvertLeadButton(){
        convertLeadButton.click();
    }
}
