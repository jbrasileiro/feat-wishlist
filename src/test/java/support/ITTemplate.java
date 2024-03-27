package support;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.wishlist.CustomApplication;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("IT")
@Import(ITConfiguration.class)
@TestPropertySource(locations = { "/IT/integration-test.properties" })
@AutoConfigureWebTestClient
@SpringBootTest(classes = CustomApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public abstract class ITTemplate {

	private static final String SERVER_URL_TEMPLATE = "http://localhost:%s/%s";
	
	
	@Getter(value = AccessLevel.PROTECTED)
	@Autowired
	private MongoTemplate mongoTemplate;
	@Getter(value = AccessLevel.PROTECTED)
	@Autowired
	private RestTemplateCommand4T restTemplate;

	@LocalServerPort
	private int port;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
	}

	@BeforeEach
	void setup() throws Exception {
		Set<String> values = getMongoTemplate().getCollectionNames();
		for (String collection : values) {
			log.warn("Deleting collection " + collection);
			getMongoTemplate().dropCollection(collection);
		}
	}

	protected String readJSON(String resource) {
		return ResourceReader4T.readJSON(resource);
	}
	
	protected URI toURI(String path) {
		return toURI(path, Collections.emptyMap());
	}

	protected URI toURI(String path, Map<String, String> values) {
		List<NameValuePair> parameters = new ArrayList<>();
		for (Entry<String, String> each : values.entrySet()) {
			parameters.add(new BasicNameValuePair(each.getKey(), each.getValue()));
		}
		try {
			return new URIBuilder(String.format(SERVER_URL_TEMPLATE, port, path)).addParameters(parameters).build();
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex);
		}
	}

}
