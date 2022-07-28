package org.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.test.etc.Access;
import org.test.etc.ConfProperties;
import org.test.etc.SeleniumConfig;
import org.test.pages.HomePage;
import org.test.pages.LoginPage;
import org.test.pages.Navbar;

public class LoginTest extends BaseTest {
    public static LoginPage loginPage;
    public static HomePage homePage;

    @BeforeClass
    public static void setup() {
        config = new SeleniumConfig("Chrome");
        config.getDriver().get(ConfProperties.getProperty("loginpage"));
        navbar = new Navbar(config.getDriver());

        loginPage = new LoginPage( config.getDriver());
        homePage = new HomePage( config.getDriver());

        Access.loginPage = loginPage;
        Access.navbar = navbar;
    }

    @Test
    public void loginTest() {
        Access.logIn(ConfProperties.getProperty("login"),
                    ConfProperties.getProperty("password"));

        Assert.assertEquals( homePage.getUserName(),
                ConfProperties.getProperty("username"));
    }

    @AfterClass
    public static void tearDown(){
        Access.logOut();
        config.getDriver().quit();
    }
}
