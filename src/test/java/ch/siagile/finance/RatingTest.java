package ch.siagile.finance;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.instrument.rating.*;

public class RatingTest {
	
	@Test public void shouldAaaEqualsToAaa() {
		assertThat(rating("Aaa"), is(equalTo(rating("Aaa"))));
	}

	private MoodyRating rating(String value) {
		return MoodyRatings.find(value);
	}

	private void should(String string) {
		if(string.contains("<")){
			String[] split = string.split(" < ");
			assertThat(rating(split[0]), is(lessThan(rating(split[1]))));
		}
		else{
			String[] split = string.split(" > ");
			assertThat(rating(split[0]), is(greaterThan(rating(split[1]))));
		}
	}

	@Test public void shouldB2IsLowerThenB1() {
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
	
}
