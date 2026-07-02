package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    private By headerTitle = By.className("oxd-topbar-header-breadcrumb-module");
    private By pimButton = By.xpath("//a[contains(@href,'pim')]");
    private By menuButton = By.className("oxd-userdropdown-icon");
    private By logoutLink = By.xpath("//a[normalize-space()='Logout']");


    public DashboardPage(WebDriver driver) {
        super(driver);
    }


    public boolean isOnDashboardPage(){
        return isElementVisible(headerTitle);
    }



    public String getHeaderText(){
        return getText(headerTitle);
    }


    public PIMPage goToPIM(){
        click(pimButton);

        return new PIMPage(driver);
    }

    public LoginPage logout(){

        click(menuButton);
        click(logoutLink);

        wait.until(ExpectedConditions.urlContains("login"));

        return new LoginPage(driver);

    }


}
