package support;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityAssert
	extends AbstractAssert<ResponseEntityAssert, ResponseEntity<String>> {

  public static ResponseEntityAssert assertThat(ResponseEntity<String> actual) {
    return new ResponseEntityAssert(actual);
  }
  
  private ResponseEntityAssert(ResponseEntity<String> actual) {
    super(actual, ResponseEntityAssert.class);
  }

  public ResponseEntityAssert hasStatus(HttpStatus status) {
    Assertions
      .assertThat(actual.getStatusCode())
      .as(() -> "\nCurrent response body:\n" + actual.getBody() + "\n")
      .isEqualTo(status);
    return this;
  }
  
  public ResponseEntityAssert responseBody(String expected) {
	  return responseBody(expected, "content[*].id", "content[*].id.timestamp", "content[*].id.date");
  }
  
  public ResponseEntityAssert responseBody(String expected, String ... ignore) {
    CustomJSONAssert
      .assertThat(actual.getBody())
      .addIgnoreValueFiled(ignore)
      .verify(expected);
    return this;
  }
  
  public ResponseEntityAssert responseBodyNull() {
    Assertions.assertThat(actual.getBody()).isNull();
    return this;
  }
  
  public ResponseEntityAssert faultResponseBody(String expected) {
    ExceptionResponseJSONAssert.assertThat(actual.getBody()).verify(expected);
    return this;
  }

  public void hasResponseBody() {
    Assertions.assertThat(actual.getBody()).isNotNull();
  }

}