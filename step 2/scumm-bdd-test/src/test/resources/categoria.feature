# new feature
# Tags: optional
    
Feature: Como restaurador quiero poder gestionar las categorias de los platos
    
Scenario: Creacion de una categoria
    Given Una categoria de nombre "Ensaladas"
    When Cuando doy de alta la categoria
    Then Aparece en el listado