package org.test.etc;

import org.test.pages.LoginPage;
import org.test.pages.Navbar;

/**
 * class to log in and log out into suiteCRM
 */
public abstract class Access {
    public static LoginPage loginPage;
    public static Navbar navbar;

    /**
     * log in method
     * @param login - login of the user
     * @param password - password of the user
     */
    public static void logIn(final String login, final String password) {
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginButton();
    }

    /**
     * log out method
     */
    public static void logOut() {
        navbar.clickMenuButton();
        navbar.clickLogoutButton();
    }

}
