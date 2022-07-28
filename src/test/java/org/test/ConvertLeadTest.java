package org.test;

import net.bytebuddy.utility.RandomString;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.etc.Access;
import org.test.etc.ConfProperties;
import org.test.etc.SeleniumConfig;
import org.test.pages.*;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ConvertLeadTest extends BaseTest {

    public static LoginPage loginPage;
    public static HomePage homePage;
    public static LeadsPage leadsPage;
    public static LeadRecordPage leadRecordPage;
    public static ConvertLeadPage convertLeadPage;
    public static ContactSearchPage contactSearchPage;
    public static ConvertedLeadPage convertedLeadPage;
    public static AccountPage accountPage;

    @BeforeClass
    public static void setup() {
        config = new SeleniumConfig("Yandex");
        config.getDriver().get(ConfProperties.getProperty("loginpage"));
        navbar = new Navbar(config.getDriver());

        loginPage = new LoginPage(config.getDriver());
        homePage = new HomePage(config.getDriver());
        leadsPage = new LeadsPage(config.getDriver());
        leadRecordPage = new LeadRecordPage(config.getDriver());
        convertLeadPage = new ConvertLeadPage(config.getDriver());
        contactSearchPage = new ContactSearchPage(config.getDriver());
        convertedLeadPage = new ConvertedLeadPage(config.getDriver());
        accountPage = new AccountPage(config.getDriver());

        Access.loginPage = loginPage;
        Access.navbar = navbar;
    }

    @Test
    public void convertLeadTest() {
        Access.logIn(ConfProperties.getProperty("login"),
                ConfProperties.getProperty("password"));

        homePage.clickNewButton();
        homePage.clickNewLeadsButton();

        final String officeNumber = "(888) 888-8888";
        final String firstName = "William";
        final String lastName = "Burger";

        leadsPage.inputOfficePhone(officeNumber);
        leadsPage.inputFirstName(firstName);
        leadsPage.inputLastName(lastName);
        leadsPage.clickSaveButton();

        leadRecordPage.clickActionsButton();
        leadRecordPage.clickConvertLeadButton();

        convertLeadPage.switchToIFrame();
        convertLeadPage.clickCreateContactCheckbox();
        convertLeadPage.clickSelectContactButton();

        String originalWindow = config.getDriver().getWindowHandle();
        {
            final var wait = new WebDriverWait(config.getDriver(), Duration.ofSeconds(15))
                    .until(numberOfWindowsToBe(2));
        }
        for (String windowHandle : config.getDriver().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                config.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
        contactSearchPage.clickClearButton();
        contactSearchPage.clickSearchButton();

        final var contactIdx = (int)( Math.random() * 20);
        final String contactName = contactSearchPage.getContactName(contactIdx);
        contactSearchPage.clickContactFromFound(contactIdx);
        {
            final var wait = new WebDriverWait(config.getDriver(), Duration.ofSeconds(15))
                    .until(numberOfWindowsToBe(1));
        }
        config.getDriver().switchTo().window(originalWindow);

        final String accountName = RandomString.make(3) + " ink.";
        convertLeadPage.switchToIFrame();
        convertLeadPage.clickSaveButton();
        Assert.assertFalse(convertLeadPage.getMissingMessages().isEmpty());

        convertLeadPage.setAccountName(accountName);
        convertLeadPage.clickSaveButton();

        convertedLeadPage.clickNewAccount();

        config.getDriver().navigate().refresh();

        final var name = accountPage.getName();
        Assert.assertEquals( name, accountName);

        accountPage.scrollToPanels();
        String currentContactName = "";
        String currentLeadName = "";
        try{
            currentContactName = accountPage.getContactName();
            currentLeadName = accountPage.getLeadName();
        } catch ( Exception e) {
            accountPage.clickContactsSubPanel();
            accountPage.clickLeadsSubPanel();

            currentContactName = accountPage.getContactName();
            currentLeadName = accountPage.getLeadName();
        }

        Assert.assertEquals( contactName, currentContactName);
        Assert.assertEquals( firstName + " " + lastName, currentLeadName);
    }

    @AfterClass
    public static void tearDown(){
        Access.logOut();
        config.getDriver().quit();
    }
}
