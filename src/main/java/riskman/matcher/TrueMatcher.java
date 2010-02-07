package riskman.matcher;

import org.hamcrest.*;

public class TrueMatcher<T> extends BaseMatcher<T> {

	public void describeTo(Description description) {
		description.appendValue("true");
	}

	public boolean matches(Object item) {
		return true;
	}
}
