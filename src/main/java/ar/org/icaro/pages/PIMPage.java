package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PIMPage extends BasePage {

    private By employeeNameField = By.cssSelector("input[placeholder='Type for hints...']");
    private By searchButton = By.className("orangehrm-left-space");
    private By noRecordsMessage = By.xpath("//span[contains(.,'No Records Found')]");
    private By searchResults = By.xpath("//span[contains(.,'Record Found')]");
    private By employeeSuggestion = By.xpath("//div[@role='listbox']//span");


    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnPIMPage(){
        return waitForUrlContains("/pim/");
    }

    public PIMPage enterEmployeeName(String name) {
        type(employeeNameField, name);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(employeeSuggestion));
            click(employeeSuggestion);
        } catch (TimeoutException e) {
            // No hubo sugerencias, se continúa con la búsqueda
        }

        return this;
    }
    public PIMPage clickSearch(){
        click(searchButton);
        return this;
    }

    public boolean searchEmployeeByName(String name) {
        enterEmployeeName(name);
        clickSearch();

        try {
            if (isNoRecordsDisplayed()) {
                return false;
            }

            return hasResults();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasResults(){
        return isElementVisible(searchResults);

    }

    public boolean isNoRecordsDisplayed(){
        return isElementVisible(noRecordsMessage);
    }




}
