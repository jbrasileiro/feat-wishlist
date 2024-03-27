package support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.api.AbstractAssert;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

public class CustomJSONAssert 
	extends AbstractAssert<CustomJSONAssert, String> {

	private final List<Customization> customizations = new ArrayList<>();
	
	public static CustomJSONAssert assertThat(String actual) {
        return new CustomJSONAssert(actual);
    }
	
	private CustomJSONAssert(String actual) {
		super(actual, CustomJSONAssert.class);
	}
	
	public void verify(String expected) {
		isNotNull();
    String currentJSON = "\ncurrent payload: \n" + actual + "\n";
    String message = new StringBuilder().append(currentJSON).toString();
		try {
      JSONAssert.assertEquals(message, expected, actual, comparator());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Customization[] customization() {
    if (customizations.isEmpty()) {
			return new Customization[] {};
		} else {
			return customizations.toArray(new Customization[customizations.size()]);
		}
	}
	
	private CustomComparator comparator() {
		return new CustomComparator(JSONCompareMode.STRICT, customization());
	}

    public CustomJSONAssert addIgnoreValueFiled(String... ignore) {
      if (ArrayUtils.isNotEmpty(ignore)) {
        Arrays
          .asList(ignore)
          .forEach(this::addIgnoreValueFiled);
      }
      return this;
    }
    
	public CustomJSONAssert addIgnoreValueFiled(String fieldName) {
		customizations.add(new Customization(fieldName, (o1, o2) -> true));
		return this;
	}

}
