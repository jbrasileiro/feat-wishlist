package acceptance;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters({
		@ConfigurationParameter(key = io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/AT/features"),
		@ConfigurationParameter(key = io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME, value = "not @Disabled")
})
public class CucumberIT {

}
