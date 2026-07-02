package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.className("orangehrm-login-button");
    private By errorMessage = By.className("oxd-alert-content-text");

    private static final String URL = "https://opensource-demo.orangehrmlive.com";


    public LoginPage(WebDriver driver){
        super(driver);
    }



    public LoginPage goTo() {

        driver.get(URL);

        return this;
    }


    public LoginPage enterUsername(String username){
        type(usernameField,username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField,password);
        return this;
    }

    public DashboardPage clickLogin(){
        click(loginButton);
        return new DashboardPage(driver);

    }

    public DashboardPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    public LoginPage loginExpectingError(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        click(loginButton);
        isElementVisible(errorMessage);
        return this;
    }

    public boolean isErrorDisplayed() {
        return isElementVisible(errorMessage);
    }

    public boolean isUsernameFieldDisplayed() {

        return isElementVisible(usernameField);
    }

    public boolean isPasswordFieldDisplayed() {

        return isElementVisible(passwordField);
    }

    public boolean isLoginButtonDisplayed() {

        return isElementVisible(loginButton);
    }

    public boolean isOnLoginPage(){

        return waitForUrlContains("login");
    }
}
