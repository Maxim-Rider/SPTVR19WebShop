/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.webinf.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.webinf.admin.AdminPanelPage;
import web.webinf.admin.ListBuyersPage;
import web.webinf.guest.LoginFormPage;

/**
 *
 * @author jvm
 */
public class MenuPage {
    protected WebDriver driver;
    private final By loginformBy = By.id("loginForm");
    private final By adminformBy = By.id("adminForm");
    private final By listBuyersBy = By.id("adminForm");
    private final By logoutBy = By.id("logout");
    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }
    public LoginFormPage getLoginFormPage(){
        driver.findElement(loginformBy).click();
        return new LoginFormPage(driver);
    }

    public AdminPanelPage getAdminFormPage() {
        driver.findElement(adminformBy).click();
        return new AdminPanelPage(driver);
    }

    public ListBuyersPage getListBuyersPage() {
        driver.findElement(By.id("listBuyers")).click();
        return new ListBuyersPage(driver);
    }

    public void logout() {
        driver.findElement(logoutBy).click();
    }
}