package ch.siagile.finance;

import static ch.siagile.finance.IsMaxMatcher.*;
import static ch.siagile.finance.IsMinMatcher.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.instrument.rating.*;

public class IsInMatcher<T> extends BaseMatcher<T> {

	public static final <T> Matcher<T> ratingIn(MoodyRating from, MoodyRating to) {
		return new IsInMatcher<T>(from, to);
	}

	private final MoodyRating from;
	private final MoodyRating to;

	public IsInMatcher(MoodyRating from, MoodyRating to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("in range from:");
		description.appendValue(from);
		description.appendText("to:");
		description.appendValue(to);
	}

	@Override
	public boolean matches(Object item) {
		Rating rating = ratingOf(item);
		if (null == rating)
			return false;
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
