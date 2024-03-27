package acceptance.steps;

import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wishlist.domain.model.Wishlist;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.ATTemplate;
import support.Constants;
import support.ResponseEntityAssert;

public class WishlistFeatureStep extends ATTemplate {
	
	private static final String RESPONSE_EMPTY = readJSON("classpath:IT/json/GET_empty_response.json");
	private static final String RESOURCE_wishlist = "wishlist";

	private static final String JSON = "classpath:AT/json/wishlist/";
	
	private ResponseEntity<String> response;
	
	@Before
	public void setUp() throws Exception {
		dropAllCollection();
	}
	
	@Given("^wishlist \"(.+)\"$")
	public void wishlist(String id) throws Throwable {
		getMongoTemplate().save(Wishlist.builder()
				.id(new ObjectId(id))
				.nome("wishlist example #" + id)
				.build());
	}
	
    @When("^sending POST request \"(.+)\" to create a new wishlist$")
    public void sendPOSTRequest(String request) throws Throwable {
    	String requestJSON = readJSON(JSON + request);
		this.response = getRestTemplate().requestPOST(toURI(RESOURCE_wishlist), requestJSON);
    }
    
    @When("^sending PUT request \"(.+)\" to update wishlist \"(.+)\"$")
    public void sendPUTRequest(String request, String id) throws Throwable {
    	String requestJSON = readJSON(JSON + request);
		this.response = getRestTemplate().requestPUT(toURI("wishlist/" + id), requestJSON);
    }
    
    @When("^sending DELETE request to wishlist \"(.+)\"$")
    public void sendDELETERequest(String id) throws Throwable {
    	this.response = getRestTemplate().requestDELETE(toURI("wishlist/" + id));
    }
    
    @Then("^DELETE wishlist response should be returned successfully$")
    public void verifyDELETEResponse() throws Throwable {
    	ResponseEntityAssert.assertThat(response)
    		.hasStatus(HttpStatus.NO_CONTENT)
    		.matches(response -> Objects.isNull(response.getBody()));
    }
    
    @Then("^wishlist operation response \"(.+)\" should be returned successfully$")
    public void verifySuccessfulResponse(String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
		ResponseEntityAssert.assertThat(response)
			.hasStatus(HttpStatus.OK)
			.responseBody(expectedResponseJSON, "id");
    }
    
    @Then("^unsuccessful wishlist operation response with (\\d+) and \"(.+)\" should be returned$")
    public void verifyUnsuccessfulResponse(int statusCode, String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
    	ResponseEntityAssert
    		.assertThat(response)
    		.hasStatus(HttpStatus.valueOf(statusCode))
    		.faultResponseBody(expectedResponseJSON);
    }
    
    @And("^wishlist information should be available response as \"(.+)\"$")
    public void verifyResponseGET(String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_wishlist));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.OK)
    		.responseBody(expectedResponseJSON);
    }
    
    @And("^no wishlist information should be available$")
    public void verifyResponseGETEmpty() throws Throwable {
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_wishlist));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.OK)
    		.responseBody(RESPONSE_EMPTY);
    }
    
    @And("^no wishlist information with id \"(.+)\" should be available$")
    public void verifyResponseGETEmpty(String id) throws Throwable {
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_wishlist + "/" + id));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.UNPROCESSABLE_ENTITY)
			.responseBody(Constants.NONEXISTENT_RESPONSE.formatted("Wishlist", id, "/wishlist/" + id));
    }
    
    @And("^wishlist information with id \"(.+)\" should be available response as \"(.+)\"$")
    public void responseGETWithID(String id, String expectedResponse) throws Throwable {
    	String expectedResponseJSON = readJSON(JSON + expectedResponse);
    	ResponseEntity<String> responseGET = getRestTemplate().requestGET(toURI(RESOURCE_wishlist + "/" + id));
    	ResponseEntityAssert.assertThat(responseGET)
    		.hasStatus(HttpStatus.OK)
    		.responseBody(expectedResponseJSON);
    }
    
}
