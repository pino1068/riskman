package riskman.matcher;

import org.hamcrest.*;

import riskman.instrument.*;
import riskman.instrument.rating.*;
import riskman.position.*;

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
		Rating rating = null;
		if(BondPosition.class.isInstance(item))
			rating = toBondPosition(item).rating();
		if(Bond.class.isInstance(item))
			rating = toBond(item).rating();
		if(Rating.class.isInstance(item))
			rating = toRating(item);
		return ratingMatches(rating);
	}

	private Rating toRating(Object item) {
		return (Rating)item;
	}

	private Bond toBond(Object item) {
		return (Bond)item;
	}

	private BondPosition toBondPosition(Object item) {
		return (BondPosition)item;
	}

	private boolean ratingMatches(Object item) {
		return Matchers.greaterThanOrEqualTo(threshold).matches(item);
	}
}
