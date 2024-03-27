package acceptance.steps;

import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wishlist.domain.model.Product;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.ATTemplate;
import support.Constants;
import support.ResponseEntityAssert;

public class ProductFeatureStep extends ATTemplate {
	
	private static final String RESPONSE_EMPTY = readJSON("classpath:IT/json/GET_empty_response.json");
	private static final String RESOURCE_PRODUCT = "product";

	private static final String JSON = "classpath:AT/json/product/";
	
	private ResponseEntity<String> response;
	
	@Before
	public void setUp() throws Exception {
		dropAllCollection();
	}
	
	@Given("^product \"(.+)\"$")
	public void product(String id) throws Throwable {
		getMongoTemplate().save(Product.builder()
				.id(new ObjectId(id))
				.nome("product example #" + id)
				.build());
	}
	
    @When("^sending POST request \"(.+)\" to create a new product$")
    public void sendPOSTRequest(String request) throws Throwable {
    	String requestJSON = readJSON(JSON + request);
		this.response = getRestTemplate().requestPOST(toURI(RESOURCE_PRODUCT), requestJSON);
    }
    
    @When("^sending PUT request \"(.+)\" to update product \"(.+)\"$")
    public void sendPUTRequest(String request, String id) throws Throwable {
    	String requestJSON = readJSON(JSON + request);
		this.response = getRestTemplate().requestPUT(toURI("product/" + id), requestJSON);
    }
    
    @When("^sending DELETE request to product \"(.+)\"$")
    public void sendDELETERequest(String id) throws Throwable {
    	this.response = getRestTemplate().requestDELETE(toURI("product/" + id));
    }
    
    @Then("^DELETE product response should be returned successfully$")
    public void verifyDELETEResponse() throws Throwable {
    	ResponseEntityAssert.assertThat(response)
    		.hasStatus(HttpStatus.NO_CONTENT)
    		.matches(response -> Objects.isNull(response.getBody()));
    }
    
    @Then("^product operation response \"(.+)\" should be returned successfully$")
    public void verifySuccessfulResponse(String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
		ResponseEntityAssert.assertThat(response)
			.hasStatus(HttpStatus.OK)
			.responseBody(expectedResponseJSON, "id");
    }
    
    @Then("^unsuccessful product operation response with (\\d+) and \"(.+)\" should be returned$")
    public void verifyUnsuccessfulResponse(int statusCode, String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
    	ResponseEntityAssert
    		.assertThat(response)
    		.hasStatus(HttpStatus.valueOf(statusCode))
    		.faultResponseBody(expectedResponseJSON);
    }
    
    @And("^product information should be available response as \"(.+)\"$")
    public void verifyResponseGET(String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_PRODUCT));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.OK)
    		.responseBody(expectedResponseJSON);
    }
    
    @And("^no product information should be available$")
    public void verifyResponseGETEmpty() throws Throwable {
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_PRODUCT));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.OK)
    		.responseBody(RESPONSE_EMPTY);
    }
    
    @And("^no product information with id \"(.+)\" should be available$")
    public void verifyResponseGETEmpty(String id) throws Throwable {
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_PRODUCT + "/" + id));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.UNPROCESSABLE_ENTITY)
			.responseBody(Constants.NONEXISTENT_RESPONSE.formatted("Product", id, "/product/" + id));
    }
    
    @And("^product information with id \"(.+)\" should be available response as \"(.+)\"$")
    public void responseGETWithID(String id, String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_PRODUCT + "/" + id));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.OK)
    		.responseBody(expectedResponseJSON);
    }
    
}
