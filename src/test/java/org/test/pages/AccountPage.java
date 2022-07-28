package org.test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class AccountPage extends Page {

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'label-container') and starts-with(.,'NAME')]/..//scrm-field")
    private WebElement nameField;
    @FindBy(xpath = "//div[contains(@class,'insight-panel-card') and contains(normalize-space(.),'Contacts')]")
    private WebElement contactsSubPanel;
    @FindBy(xpath = "//div[contains(@class,'insight-panel-card') and contains(normalize-space(.),'Leads')]")
    private WebElement leadsSubPanel;
    @FindBy(xpath = "//div[@id='sub-panels']//div[contains(.,'Contacts')]/..//thead[contains(.,'Name')]/..//td[contains(@class,'cdk-column-name')][1]")
    private WebElement contactNameField;
    @FindBy(xpath = "//div[@id='sub-panels']//div[contains(.,'Leads')]/..//thead[contains(.,'Name')]/..//td[contains(@class,'cdk-column-name')][1]")
    private WebElement leadNameField;
    @FindBy(xpath = "//*[@id='sub-panel-buttons']")
    private WebElement panels;

    /**
     * text from element that contains username and located by xpath :
     * //div[contains(@class,'label-container') and starts-with(.,'NAME')]/..//scrm-field
     * @return name of created lead
     */
    public String getName(){
        wait.until(ExpectedConditions.visibilityOf(nameField));
        return nameField.getText();
    }
    /**
     * clicks on element located by xpath :
     * //div[contains(@class,'insight-panel-card') and contains(normalize-space(.),'Contacts')]
     */
    public void clickContactsSubPanel() {
        wait.until(ExpectedConditions.visibilityOf(contactsSubPanel));
        wait.until(ExpectedConditions.elementToBeClickable(contactsSubPanel));
        contactsSubPanel.click();
    }
    /**
     * clicks on element located by xpath :
     * //div[contains(@class,'insight-panel-card') and contains(normalize-space(.),'Leads')]
     */
    public void clickLeadsSubPanel() {
        wait.until(ExpectedConditions.visibilityOf(leadsSubPanel));
        wait.until(ExpectedConditions.elementToBeClickable(leadsSubPanel));
        leadsSubPanel.click();
    }
    /**
     * returns text on element located by xpath :
     * //div[@id='sub-panels']//div[contains(.,'Contacts')]/..//thead[contains(.,'Name')]/..//td[contains(@class,'cdk-column-name')][1]
     * @return name of contact assigned to account
     */
    public String getContactName() {
        wait.until(ExpectedConditions.visibilityOf(contactNameField));
        return contactNameField.getText();
    }
    /**
     * returns text on element located by xpath :
     * //div[@id='sub-panels']//div[contains(.,'Leads')]/..//thead[contains(.,'Name')]/..//td[contains(@class,'cdk-column-name')][1]
     * @return name of contact assigned to account
     */
    public String getLeadName() {
        wait.until(ExpectedConditions.visibilityOf(leadNameField));
        return leadNameField.getText();
    }

    /**
     * scroll to element located by xpath :
     * //*[@id='sub-panels']
     */
    public void scrollToPanels(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", panels);
        wait.until(ExpectedConditions.visibilityOf(panels));
    }
}