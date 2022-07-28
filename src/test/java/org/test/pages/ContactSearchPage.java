package org.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ContactSearchPage extends Page {

    public ContactSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='search_form_clear']")
    private WebElement clearButton;

    @FindBy(xpath = "//input[@id='search_form_submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//table[contains(@class,'list view')]//td[contains(@class,'ListRowS')][1]/a")
    private List<WebElement> foundContacts;

    /**
     * clicks element located by xpath :
     * //input[@id='search_form_clear']
     */
    public void clickClearButton(){
        wait.until(ExpectedConditions.elementToBeClickable(clearButton));
        clearButton.click();
    }
    /**
     * clicks element located by xpath :
     * //input[@id='search_form_submit']
     */
    public void clickSearchButton(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }
    /**
     * clicks one of elements located by xpath :
     * //table[contains(@class,'list view')]//td[contains(@class,'ListRowS')][1]/a
     * @param idx - number of contact
     */
    public void clickContactFromFound(int idx){
        wait.until(ExpectedConditions.elementToBeClickable(foundContacts.get(idx)));
        foundContacts.get(idx).click();
    }

    /**
     * get text from one of elements located by xpath :
     * //table[contains(@class,'list view')]//td[contains(@class,'ListRowS')][1]/a
     * @param idx - number of contact
     * @return name of contact with number = idx
     */
    public String getContactName(int idx){
        wait.until(ExpectedConditions.visibilityOf(foundContacts.get(idx)));
        return foundContacts.get(idx).getText();
    }
}
