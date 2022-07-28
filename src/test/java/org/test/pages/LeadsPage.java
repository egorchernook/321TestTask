package org.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

// page_url = https://suite8demo.suiteondemand.com/#/leads/edit
public class LeadsPage extends Page {

    public LeadsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'field-group-item') and *[contains(.,'Last Name')]]//input[@type='text']")
    private WebElement lastNameField;
    @FindBy(xpath = "//div[contains(@class,'field-group-item') and *[contains(.,'First Name')]]//input[@type='text']")
    private WebElement firstNameField;
    @FindBy(xpath = "//div[contains(@class,'form-group') and *[contains(.,'OFFICE PHONE')]]//input[@type='text']")
    private WebElement officePhoneField;
    @FindBy(xpath = "//button[contains(@type,'button') and contains(@class, 'settings-button') and contains(., 'Save')]")
    private WebElement saveButton;
    @FindBy(xpath = "//div[contains(@class, 'invalid-feedback') and *[contains(.,'Missing required field')]]")
    private List<WebElement> missingMessages;

    /**
     * returns list of elements with error messages located by xpath :
     * //div[contains(@class, 'invalid-feedback') and *[contains(.,'Missing required field')]]
     */
    public List<WebElement> getMissingMessages(){
        return missingMessages;
    }
    /**
     * clicks element located by xpath :
     * //button[contains(@type,'button') and contains(@class, 'settings-button') and contains(., 'Save')]
     */
    public void clickSaveButton(){
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    /**
     * input first name into input-field located by xpath :
     * //div[contains(@class,'field-group-item') and *[contains(.,'First Name')]]//input[@type='text']
     * @param firstName - first name of created lead
     */
    public void inputFirstName(final String firstName){
        firstNameField.sendKeys(firstName);
    }

    /**
     * input last name into input-field located by xpath :
     * //div[contains(@class,'field-group-item') and *[contains(.,'Last Name')]]//input[@type='text']
     * @param lastName - last name of created lead
     */
    public void inputLastName(final String lastName){
        lastNameField.sendKeys(lastName);
    }

    /**
     * input office phone into input-field located by xpath :
     * //div[contains(@class,'form-group') and *[contains(.,'OFFICE PHONE')]]//input[@type='text']
     * @param officePhone - office phone of created lead
     */
    public void inputOfficePhone(final String officePhone){
        officePhoneField.sendKeys(officePhone);
    }
}