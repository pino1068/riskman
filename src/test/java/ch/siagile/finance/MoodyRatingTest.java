package ch.siagile.finance;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.*;
import org.junit.*;

import ch.siagile.finance.instrument.rating.*;

public class MoodyRatingTest {

	@Test
	public void shouldCompare2RatingsUsingNaturalOrder() {
		should("Aaa = Aaa");
		should("Aaa > Aa1");
		should("Aa1 < Aaa");
		should("A2 < Aaa");
		should("A2 < A1");
		should("B2 < A1");
		should("B2 < B1");
		should("B1 > B2");
		should("Baa1 > Ca");
		should("A2 > C");
		should("C < A2");
	}

	private MoodyRating rating(String value) {
		return MoodyRatings.find(value);
	}

	private void should(String string) {
		String cleanString = clean(string);
		should(cleanString, operator(cleanString));
	}

	private String clean(String string) {
		return string.replaceAll(" ", "");
	}

	private String operator(String string) {
		if (string.contains("="))
			return "=";
		if (string.contains("<"))
			return "<";
		return ">";
	}

	private void should(String string, String operator) {
		String[] split = string.split(operator);
		MoodyRating rating = rating(split[1]);
		Matcher<MoodyRating> matcher = matcher(operator, rating);
		assertThat(rating(split[0]), is(matcher));
	}

	private Matcher<MoodyRating> matcher(String operator, MoodyRating rating) {
		if ("=".equals(operator)) {
			return equalTo(rating);
		}
		if ("<".equals(operator)) {
			return lessThan(rating);
		}
		return greaterThan(rating);
	}
}
