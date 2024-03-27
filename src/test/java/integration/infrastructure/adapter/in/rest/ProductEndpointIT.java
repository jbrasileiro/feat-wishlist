package integration.infrastructure.adapter.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import support.ITTemplate;
import support.ResponseEntityAssert;

class ProductEndpointIT extends ITTemplate {

	private static final String JSON = "classpath:IT/json/product/";

	@Test
	void sendPOSTRequest() {
		String request = readJSON(JSON + "/POST_product_example_request.json");
		String expected = readJSON(JSON + "/POST_product_example_response.json");
		final ResponseEntity<String> response = getRestTemplate().requestPOST(toURI("product"), request);
		ResponseEntityAssert.assertThat(response)
			.hasStatus(HttpStatus.OK)
			.responseBody(expected, "id.date", "id.timestamp");
	}
	
	@Test
	void sendPUTRequestWithNonexistentID() {
		String request = readJSON(JSON + "/PUT_product_example_request.json");
		String expected = readJSON(JSON + "/nonexistent_product_response.json");
		final ResponseEntity<String> response = getRestTemplate().requestPUT(toURI("product/000000000000000000000000"), request);
		ResponseEntityAssert.assertThat(response)
			.hasStatus(HttpStatus.UNPROCESSABLE_ENTITY)
			.responseBody(expected);
	}

}
