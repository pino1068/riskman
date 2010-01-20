package ch.siagile.finance.matcher;

import org.hamcrest.*;

public class TrueMatcher<T> extends BaseMatcher<T> {
	@Override
	public void describeTo(Description description) {
		description.appendValue("true");
	}

	@Override
	public boolean matches(Object item) {
		return true;
	}
}
