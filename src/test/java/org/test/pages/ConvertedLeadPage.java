package org.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

//page_url = https://suite8demo.suiteondemand.com/#/leads/convert-lead/*
public class ConvertedLeadPage extends Page {

    public ConvertedLeadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "a[contains(normalize-space(.),'Select')]")
    private WebElement selectButton;
    @FindBy(xpath = "//tr[contains(.,'Select')]/td[2]/a")
    private WebElement accountButton;
    @FindBy(xpath = "//li[contains(.,'Created a new Account')]/a")
    private WebElement newAccount;

    /**
     * clicks element located by xpath :
     * a[contains(normalize-space(.),'Select')]
     * works when used an existing account
     */
    public void clickSelectButton(){
        new Actions(driver)
                .moveToElement(selectButton)
                .click()
                .perform();
    }
    /**
     * clicks element located by xpath :
     * //tr[contains(.,'Select')]/td[2]/a
     * works when used an existing account
     */
    public void clickAccountButton(){
        wait.until(ExpectedConditions.visibilityOf(accountButton));
        accountButton.click();
    }
    /**
     * clicks element located by xpath :
     * //li[contains(.,'Created a new Account')]/a
     * works when new account created
     */
    public void clickNewAccount(){
        wait.until(ExpectedConditions.visibilityOf(newAccount));
        newAccount.click();
    }
}