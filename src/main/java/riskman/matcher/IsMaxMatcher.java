package riskman.matcher;

import org.hamcrest.*;

import riskman.instrument.rating.*;

public class IsMaxMatcher<T> extends BaseMatcher<T> {
	private MoodyRating threshold;

	public IsMaxMatcher(MoodyRating threshold) {
		this.threshold = threshold;
	}

	public void describeTo(Description description) {
		description.appendValue(threshold);
	}
	
	public boolean matches(Object item) {
		return Matchers.lessThanOrEqualTo(threshold).matches(item);
	}

	public static final <T> Matcher<T> max(MoodyRating threshold) {
		return new IsMaxMatcher<T>(threshold);
	}

}
