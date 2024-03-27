package support;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ATConfiguration {

  @Bean
  public RestTemplateCommand4T restTemplateCommand(TestRestTemplate restTemplate) {
    return new RestTemplateCommand4T(restTemplate);
  }
  
}
