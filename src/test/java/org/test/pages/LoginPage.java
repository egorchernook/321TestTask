package org.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

//page_url = https://suite8demo.suiteondemand.com/#/Login
public class LoginPage extends Page {

    public LoginPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement loginField;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@id='login-button']")
    private WebElement loginButton;

    /**
     * input login into input-field located by xpath :
     * //input[@name='username']
     * @param login - login in SuiteCRM
     */
    public void inputLogin(final String login){
        loginField.sendKeys(login);
    }
    /**
     * input password into input-field located by xpath :
     * //input[@name='password']
     * @param password - password in SuiteCRM
     */
    public void inputPassword(final String password){
        passwordField.sendKeys(password);
    }
    /**
     * clicks element located by xpath :
     * //button[@id='login-button']
     */
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
}