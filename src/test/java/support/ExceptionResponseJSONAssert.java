package support;
import org.assertj.core.api.AbstractAssert;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

public class ExceptionResponseJSONAssert
	extends AbstractAssert<ExceptionResponseJSONAssert, String> {

	public static ExceptionResponseJSONAssert assertThat(String actual) {
        return new ExceptionResponseJSONAssert(actual);
    }
	
	private ExceptionResponseJSONAssert(String actual) {
		super(actual, ExceptionResponseJSONAssert.class);
	}
	
	public void verify(String expected) {
		isNotNull();
		String message = "current payload: \n" + this.actual + "\n";
		try {
			JSONAssert.assertEquals(message, expected, this.actual, comparator());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private CustomComparator comparator() {
		return new CustomComparator(JSONCompareMode.STRICT
				, new Customization("timestamp", (o1, o2) -> true) // ignore node value timestamp
		);
	}

}