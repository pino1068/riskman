package ch.siagile.finance;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import ch.siagile.finance.instrument.rating.*;

public class RatingTest {
	
	@Test public void shouldAaaEqualsToAaa() {
		assertThat(rating("Aaa"), is(equalTo(rating("Aaa"))));
	}

	private MoodyRating rating(String value) {
		return MoodyRating.from(value);
	}
	
	@Test public void shouldAaaIsGreaterThenAa1() {
		assertThat(rating("Aaa"), is(greaterThan(rating("Aa1"))));
	}
	
	@Ignore
	@Test public void shouldAa1IsLowerThenAaa() {
		assertThat(rating("Aaa"), is(lessThan(rating("Aa1"))));
	}
	
	
}
