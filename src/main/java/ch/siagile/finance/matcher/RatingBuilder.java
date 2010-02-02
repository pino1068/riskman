package ch.siagile.finance.matcher;

import static java.text.MessageFormat.*;

import org.hamcrest.*;

import ch.siagile.finance.instrument.rating.*;
import ch.siagile.finance.matcher.builder.*;
import ch.siagile.finance.position.*;

public class RatingBuilder<T> implements MatcherBuilder {

	private static final String SHARP_SEPARATOR = "#";
	private static final String DOT_SEPARATOR = ":";
	private static final String MIN = "min";
	private static final String MAX = "max";
	private static final String RANGE = "range";

	@SuppressWarnings("unchecked")
	public Matcher<Position> build(String definition) {
		return (Matcher<Position>) buildWithType(definition);
	}

	public Matcher<T> buildWithType(String string) {
		if (isMin(string)) {
			return min(string);
		}
		if (isRange(string)) {
			return range(string);
		}
		if (isMax(string)) {
			return max(string);
		}
		throw new RuntimeException(format("use only range, min, max", string));
	}

	private static boolean isMin(String string) {
		return string.startsWith(MIN);
	}

	private static boolean isMax(String string) {
		return string.startsWith(MAX);
	}

	private static boolean isRange(String string) {
		return string.startsWith(RANGE);
	}

	private static <T> Matcher<T> min(String string) {
		String clean = clean(string, MIN);
		return new IsMinMatcher<T>(MoodyRating.from(clean));
	}

	private static String clean(String source, String wordToRemove) {
		return source.replaceAll(wordToRemove + DOT_SEPARATOR, "").replaceAll(
				wordToRemove + SHARP_SEPARATOR, "");
	}

	private static <T> Matcher<T> max(String string) {
		return new IsMaxMatcher<T>(MoodyRating.from(clean(string, MAX)));
	}

	private static <T> Matcher<T> range(String string) {
		String[] tokens = clean(string, RANGE).split(",");
		String from = tokens[0];
		String to = tokens[1];
		return IsRatingInMatcher.ratingIn(MoodyRating.from(from), MoodyRating
				.from(to));
	}

	public boolean canBuild(String string) {
		return string.startsWith(MIN) || string.startsWith(RANGE)
				|| string.startsWith(MAX);
	}

	public String cleanUp(String definition) {
		return 
		definition.replaceAll("max:", "max#")
		.replaceAll("min:", "max#")
		.replaceAll("range:", "range#");
	}
}
