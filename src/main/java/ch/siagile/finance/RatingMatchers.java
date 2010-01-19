package ch.siagile.finance;

import org.hamcrest.*;

import static java.text.MessageFormat.*;

import ch.siagile.finance.instrument.rating.*;

public class RatingMatchers {

	public static <T> Matcher<T> build(String string) {
		if (isMin(string)) {
			return min(string);
		}
		if (isRange(string)) {
			return range(string);
		}
		if(isMax(string)) {
			return max(string);
		}
		throw new RuntimeException(format("use only range, min, max", string));
	}

	private static boolean isMin(String string) {
		return string.startsWith("min:");
	}

	private static boolean isMax(String string) {
		return string.startsWith("max:");
	}
	
	private static boolean isRange(String string) {
		return string.startsWith("range:");
	}

	private static <T> Matcher<T> min(String string) {
		String threshould = string.replaceAll("min:", "");
		return new IsMinMatcher<T>(MoodyRating.from(threshould));
	}

	private static <T> Matcher<T> max(String string) {
		String threshould = string.replaceAll("max:", "");
		return new IsMaxMatcher<T>(MoodyRating.from(threshould));
	}
	
	private static <T> Matcher<T> range(String string) {
		String[] tokens = string.replaceAll("range:", "").split(",");
		String from = tokens[0];
		String to = tokens[1];
		return IsInMatcher.ratingIn(MoodyRating.from(from), MoodyRating.from(to));
	}

}
