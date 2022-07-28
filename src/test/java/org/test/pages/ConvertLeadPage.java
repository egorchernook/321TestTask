package org.test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class ConvertLeadPage extends Page {

    public ConvertLeadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='newContacts']")
    private WebElement createContactCheckbox;
    @FindBy(xpath = "//button[@id='btn_report_to_name']")
    private WebElement selectContactButton;
    @FindBy(xpath = "//input[@id='Accountsname']")
    private WebElement accountNameField;
    @FindBy(xpath = "//div[contains(@class, 'validation-message')]")
    private List<WebElement> missingMessages;
    @FindBy(xpath = "//input[@id='SAVE_HEADER']")
    private WebElement saveButton;
    @FindBy(xpath = "//iframe")
    private WebElement elementIframe;

    public void switchToIFrame() {
        driver.switchTo().frame(elementIframe);
    }
    public void switchToTopLevel() {
        driver.switchTo().defaultContent();
    }
    /**
     * clicks element located by xpath :
     * //input[@id='newContacts']
     */
    public void clickCreateContactCheckbox(){
        createContactCheckbox.click();
    }
    /**
     * clicks element located by xpath :
     * //button[@id='btn_report_to_name']
     */
    public void clickSelectContactButton(){
        wait.until(ExpectedConditions.elementToBeClickable(selectContactButton));
        final var action = new Actions(driver);
        action.moveToElement(selectContactButton).click().perform();
    }
    /**
     * input name into input-field located by xpath :
     * //input[@id='Accountsname']
     */
    public void setAccountName( String name){
        accountNameField.sendKeys( name);
    }
    /**
     * returns list of elements with error messages located by xpath :
     * //div[contains(@class, 'validation-message')]
     */
    public List<WebElement> getMissingMessages(){
        return missingMessages;
    }
    /**
     *
     * clicks element located by xpath :
     * //input[@id='SAVE_HEADER']
     */
    public void clickSaveButton(){
        wait.until(ExpectedConditions.visibilityOf(saveButton));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        new Actions(driver)
                .moveToElement(saveButton)
                .pause(Duration.ofSeconds(5))
                .click()
                .perform();
    }
}
