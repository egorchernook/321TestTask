package org.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.etc.Access;
import org.test.etc.ConfProperties;
import org.test.etc.SeleniumConfig;
import org.test.pages.*;

import java.time.Duration;

public class CreateLeadTest extends BaseTest {

    public static LoginPage loginPage;
    public static HomePage homePage;
    public static LeadsPage leadsPage;
    public static LeadRecordPage leadsRecordPage;

    @BeforeClass
    public static void setup() {
        config = new SeleniumConfig("Chrome");
        config.getDriver().get(ConfProperties.getProperty("loginpage"));
        navbar = new Navbar(config.getDriver());

        loginPage = new LoginPage( config.getDriver());
        homePage = new HomePage( config.getDriver());
        leadsPage = new LeadsPage(config.getDriver());
        leadsRecordPage = new LeadRecordPage(config.getDriver());

        Access.loginPage = loginPage;
        Access.navbar = navbar;
    }

    @Test
    public void createLeadTest() {
        Access.logIn(ConfProperties.getProperty("login"),
                ConfProperties.getProperty("password"));

        homePage.clickNewButton();
        homePage.clickNewLeadsButton();

        final String officeNumber = "(888) 888-8888";
        final String firstName = "William";
        final String lastName = "Burger";

        leadsPage.inputOfficePhone(officeNumber);

        leadsPage.clickSaveButton();
        Assert.assertNotNull(leadsPage.getMissingMessages());
        leadsPage.inputFirstName(firstName);
        leadsPage.inputLastName(lastName);
        leadsPage.clickSaveButton();

        Assert.assertTrue(leadsPage.getMissingMessages().isEmpty());

        final String name = leadsRecordPage.getName();
        Assert.assertEquals( firstName + "\n" + lastName, name);
    }

    @AfterClass
    public static void tearDown(){
        Access.logOut();
        config.getDriver().quit();
    }
}
