package support;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;

public class RestTemplateCommand4T {
  
  private TestRestTemplate restTemplate;
  
  public RestTemplateCommand4T(TestRestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  
  /*
   * GET
   */
  public ResponseEntity<String> requestGET(URI url) {
    return requestGET(url, String.class, Collections.emptyMap());
  }

  public ResponseEntity<String> requestGET(URI url, Map<String, List<String>> headers) {
    return requestGET(url, String.class, headers);
  }

  public <T> ResponseEntity<T> requestGET(URI url,
      Class<T> clazz,
      Map<String, List<String>> headers) {
    return requestJSON(url, null, clazz, HttpMethod.GET, headers);
  }
  
  
  /*
   * POST
   */
  public ResponseEntity<String> requestPOST(URI uri) {
    return requestPOST(uri, null);
  }

  public ResponseEntity<String> requestPOST(URI uri, Object request) {
    return requestPOST(uri, request, String.class);
  }
  
  public ResponseEntity<String> requestPOST(URI uri, Object request, Map<String, List<String>> headers) {
    return requestPOST(uri, request, String.class, headers);
  }

  public <T> ResponseEntity<T> requestPOST(URI uri, Object request, Class<T> clazz) {
    return requestPOST(uri, request, clazz, Collections.emptyMap());
  }
  
  public <T> ResponseEntity<T> requestPOST(URI uri, 
      Object request, 
      Class<T> clazz, 
      Map<String, List<String>> headers) {
    return requestJSON(uri, request, clazz, HttpMethod.POST, headers);
  }
  
  /*
   * PUT
   */
  public ResponseEntity<String> requestPUT(URI uri, Object request) {
    return requestPUT(uri, request, String.class, Collections.emptyMap());
  }
  
  public ResponseEntity<String> requestPUT(URI uri,
      String request, 
      Map<String, List<String>> headers) {
    return requestPUT(uri, request, String.class, headers);
  }
  
  public <T> ResponseEntity<T> requestPUT(URI uri, 
      Object request, 
      Class<T> clazz) {
    return requestPUT(uri, request, clazz, Collections.emptyMap());
  }
  
  public <T> ResponseEntity<T> requestPUT(URI uri, 
      Object request, 
      Class<T> clazz, 
      Map<String, List<String>> headers) {
    return requestJSON(uri, request, clazz, HttpMethod.PUT, headers);
  }
  
  /*
   * PATCH
   */
  public ResponseEntity<String> requestPATCH(URI uri) {
    return requestPATCH(uri, null, String.class, Collections.emptyMap());
  }
  
  public ResponseEntity<String> requestPATCH(URI uri, Object request) {
    return requestPATCH(uri, request, String.class, Collections.emptyMap());
  }
  
  public <T> ResponseEntity<T> requestPATCH(URI uri, 
      Object request, 
      Class<T> clazz) {
    return requestPATCH(uri, request, clazz, Collections.emptyMap());
  }
  
  public <T> ResponseEntity<T> requestPATCH(URI uri, 
      Object request, 
      Class<T> clazz,  
      Map<String, List<String>> headers) {
    return requestJSON(uri, request, clazz, HttpMethod.PATCH, headers);
  }
  
  
  /*
   * DELETE
   */
  public ResponseEntity<String> requestDELETE(URI uri) {
    return requestDELETE(uri, null, String.class, Collections.emptyMap());
  }
  public ResponseEntity<String> requestDELETE(URI uri, Map<String, List<String>> headers) {
    return requestDELETE(uri, null, String.class, headers);
  }
  
  public <T> ResponseEntity<T> requestDELETE(URI uri,
      Object request,
      Class<T> clazz,
      Map<String, List<String>> headers) {
    return requestJSON(uri, request, clazz, HttpMethod.DELETE, headers);
  }

  /*
   * REQUEST any
   */
  private <T> ResponseEntity<T> requestJSON(URI uri, 
      Object request, 
      Class<T> clazz,
      HttpMethod httpMethod,
      Map<String, List<String>> mapHeaders) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.addAll(new MultiValueMapAdapter<>(mapHeaders));
      headers.putIfAbsent(HttpHeaders.CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON_VALUE));
      HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
      return restTemplate.exchange(uri, httpMethod, requestEntity, clazz);
    } catch (Exception e) {
      throw new RuntimeException("URI : " + uri.toString(), e);
    }
  }

}
