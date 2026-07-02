Feature: Flujo completo en OrangeHRM

  Scenario: Login exitoso
    Given estoy en la página de login de OrangeHRM
    When ingreso el usuario "Admin"
    And ingreso la contraseña "admin123"
    And hago click en el botón Login
    Then debería ver el título "Dashboard"

  Scenario: Buscar empleado existente
    Given estoy en la página de login de OrangeHRM
    When ingreso el usuario "Admin"
    And ingreso la contraseña "admin123"
    And hago click en el botón Login
    And estoy en la página de Dashboard
    And hago click en el botón PIM
    And debería ver la página de PIM
    And busco al empleado "Smith" y hago click en el botón Search
    Then debería ver los resultados de la búsqueda

  Scenario: Buscar empleado inexistente
    Given estoy en la página de login de OrangeHRM
    When ingreso el usuario "Admin"
    And ingreso la contraseña "admin123"
    And hago click en el botón Login
    And estoy en la página de Dashboard
    And hago click en el botón PIM
    And debería ver la página de PIM
    And busco al empleado "Empleado Inexistente" y hago click en el botón Search
    Then debería ver el mensaje "No Records Found"


  Scenario: Flujo completo E2E
    Given estoy en la página de login de OrangeHRM
    When ingreso el usuario "Admin"
    And ingreso la contraseña "admin123"
    And hago click en el botón Login
    And hago click en el botón PIM
    And busco al empleado "Smith" y hago click en el botón Search
    And hago click en el menú de usuario y hago click en Logout
    Then debería ver la página de Login