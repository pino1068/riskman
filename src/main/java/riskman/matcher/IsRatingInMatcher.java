package riskman.matcher;

import static riskman.matcher.IsMaxMatcher.*;
import static riskman.matcher.IsMinMatcher.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.*;

import riskman.instrument.*;
import riskman.instrument.rating.*;
import riskman.position.*;

public class IsRatingInMatcher<T> extends BaseMatcher<T> {

	public static final <T> Matcher<T> ratingIn(MoodyRating from, MoodyRating to) {
		return new IsRatingInMatcher<T>(from, to);
	}

	private final MoodyRating from;
	private final MoodyRating to;

	public IsRatingInMatcher(MoodyRating from, MoodyRating to) {
		this.from = from;
		this.to = to;
	}

	public void describeTo(Description description) {
		description
		.appendText("in range from:")
		.appendValue(from)
		.appendText("to:")
		.appendValue(to);
	}

	@SuppressWarnings("unchecked")
	public boolean matches(Object item) {
		Rating rating = ratingOf(item);
		if (null == rating)		return false;
		return allOf(min(from), max(to)).matches(rating);
	}

	private Rating ratingOf(Object item) {
		if (BondPosition.class.isInstance(item))
			return ((BondPosition) item).rating();
		
		if (MoodyRating.class.isInstance(item))
			return (Rating) item;

		if (Bond.class.isInstance(item))
			return ((Bond) item).rating();

		return null;
	}
}
