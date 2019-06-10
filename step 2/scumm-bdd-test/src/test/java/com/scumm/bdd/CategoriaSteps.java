package com.scumm.bdd;

import com.scumm.bdd.contracts.api.Category;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class CategoriaSteps {

    private String categoryName;
    private Category category;

    @Given("Una categoria de nombre {string}")
    public void dada_una_categroia(String categoryName){

        this.categoryName = categoryName;
    }

    @When("Cuando doy de alta la categoria")
    public void cuando_doy_de_alta_la_categoria() throws IOException {
        category = new Category();
        category.setName(categoryName);
        Call<Category> result = ScummApi.getInstance().getService().createCategory(category);
        Response<Category> response = result.execute();
        category.setId(response.body().getId());
    }

    @Then("Aparece en el listado")
    public void aparece_en_el_listado() throws IOException {
        Call<Category> call = ScummApi.getInstance().getService().getCategory(this.category.getId());
        Response<Category> response = call.execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(categoryName, response.body().getName());
    }
}
