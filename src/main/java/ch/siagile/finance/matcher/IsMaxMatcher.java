package ch.siagile.finance.matcher;

import org.hamcrest.*;

import ch.siagile.finance.instrument.*;

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
