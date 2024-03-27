package acceptance;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.wishlist.CustomApplication;

import io.cucumber.spring.CucumberContextConfiguration;
import support.ATConfiguration;

@ActiveProfiles({"cucumber-AT", "AT"})
@Import(ATConfiguration.class)
@CucumberContextConfiguration
@TestPropertySource(locations = { "/AT/acceptance-test.properties" })
@AutoConfigureWebTestClient
@SpringBootTest(classes = CustomApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberConfiguration {

}
