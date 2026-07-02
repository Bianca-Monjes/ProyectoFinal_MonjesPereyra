package ar.org.icaro.steps;

import ar.org.icaro.pages.DashboardPage;
import ar.org.icaro.pages.LoginPage;
import ar.org.icaro.pages.PIMPage;
import ar.org.icaro.runner.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class FlujoPrincipalSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @Given("estoy en la página de login de OrangeHRM")
    public void estoyenlapaginadelogin(){
        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();
        System.out.println("  → Navegando a la página de login");
    }

    @When("ingreso el usuario {string}")
    public void ingresoElUsuario(String usuario){
        loginPage.enterUsername(usuario);
        System.out.println("  → Ingresando usuario: " + usuario);
    }

    @And("ingreso la contraseña {string}")
    public void ingresoLaContrasena(String password){
        loginPage.enterPassword(password);
        System.out.println("  → Ingresando contraseña: **** ");
    }

    @And("hago click en el botón Login")
    public void clickEnElBotonLogin(){
        dashboardPage = loginPage.clickLogin();
        System.out.println("  → Click en el botón Login ");
    }

    @Then("debería ver el título {string}")
    public void verificoSiElTiruloEsDashboard(String tituloEsperado){
        dashboardPage = new DashboardPage(Hooks.driver);
        String tituloActual = dashboardPage.getHeaderText();
        Assert.assertEquals(tituloActual, tituloEsperado, "El título no coincide");
        System.out.println("   ✓ Verificado: El título es '" + tituloActual + "'");

    }



    @And("estoy en la página de Dashboard")
    public void estoyEnElDashboard(){
        dashboardPage = new DashboardPage(Hooks.driver);
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), "Debería estar en la página de Dashboard");
        System.out.println("   ✓ Verificado: Estamos en la página de Dashboard");
    }

    @And("hago click en el botón PIM")
    public void clickEnElBotonPIM(){
        pimPage = dashboardPage.goToPIM();
        System.out.println("  → Navegando a la página de PIM");
    }

    @And("debería ver la página de PIM")
    public void estoyEnElPIM(){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isOnPIMPage(), "Debería estar en la página de PIM");
        System.out.println("   ✓ Verificado: Estamos en la página de PIM");
    }

    @And("busco al empleado {string} y hago click en el botón Search")
    public void buscarEmpleado(String empleado){
        pimPage.searchEmployeeByName(empleado);
        System.out.println("  → Buscando al empleado: " + empleado);
    }

    @Then("debería ver los resultados de la búsqueda")
    public void resultadosDeLaBusqueda(){
        Assert.assertTrue(pimPage.hasResults(), "Deberían existir resultados para la búsqueda");
        System.out.println("   ✓ Verificado: Se muestran los resultados de la búsqueda");

    }

    @Then("debería ver el mensaje {string}")
    public void deberiaVerElMensaje(String mensaje){
        Assert.assertTrue(pimPage.isNoRecordsDisplayed());
    }



    @And("hago click en el menú de usuario y hago click en Logout")
    public void meDeslogueo(){
        loginPage = dashboardPage.logout();
        System.out.println("  → Abro el menu y hago click en el botón Logout");
    }


    @Then("debería ver la página de Login")
    public void estoyEnElLogin(){
        Assert.assertTrue(loginPage.isOnLoginPage(), "Debería estar en la página de Login");
        System.out.println("   ✓ Verificado: Estamos deslogeados");
    }

}
