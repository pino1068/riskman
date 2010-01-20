package ch.siagile.finance.matcher;

import org.hamcrest.*;

import ch.siagile.finance.instrument.*;

public class IsMinMatcher<T> extends BaseMatcher<T> {

	public static final <T> Matcher<T> min(MoodyRating threshold) {
		return new IsMinMatcher<T>(threshold);
	}

	private final MoodyRating threshold;

	public IsMinMatcher(MoodyRating threshold) {
		this.threshold = threshold;
	}

	public void describeTo(Description description) {
		description.appendValue(threshold);
	}

	public boolean matches(Object item) {
		return Matchers.greaterThanOrEqualTo(threshold).matches(item);
	}
}
