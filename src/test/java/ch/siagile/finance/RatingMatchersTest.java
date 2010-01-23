package ch.siagile.finance;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.*;

import ch.siagile.finance.instrument.rating.*;
import ch.siagile.finance.matcher.*;


public class RatingMatchersTest {

	@Test
	public void shouldInRange() {
		assertThat(RatingMatchers.build("range:C,Aaa"), is(instanceOf(IsRatingInMatcher.class)));		
	}

	@Test
	public void shouldMinA1() {
		assertThat(RatingMatchers.build("min:A1"), is(instanceOf(IsMinMatcher.class)));		
	}

	@Test
	public void shouldMaxA2() {
		assertThat(RatingMatchers.build("max:A2"), is(instanceOf(IsMaxMatcher.class)));		
	}

	@Test
	public void shouldThrowsExceptionIfNotRecognize() {
		try {
			RatingMatchers.build("unknown:");
			fail("expected exception for unknown rating matchers");
		} catch (Exception expectedBehaivor) {
		}
	}
	
	@Test
	public void shouldIsMinA1() {
		Matcher<MoodyRating> inMin = RatingMatchers.build("min:A1");
		assertThat(MoodyRating.from("Aaa"), is(inMin));
	}
	
	@Test
	public void shouldAaa1IsMaxA1() {
		Matcher<MoodyRating> inMax = RatingMatchers.build("max:A1");
		assertThat(MoodyRating.from("A2"), is(inMax));
	}

	@Test
	public void shouldA3IsMinB1() {
		Matcher<MoodyRating> inMin = RatingMatchers.build("min:B1");
		assertThat(MoodyRating.from("A3"), is(inMin));
	}
	
	@Test
	public void shouldExtractFromRange() {
		Matcher<MoodyRating> inRange = RatingMatchers.build("range:C,Aaa");
		assertThat(MoodyRating.from("C"), is(inRange));
	}
	
	@Test
	public void shouldExtractFromRangeCandA1() {
		Matcher<MoodyRating> inRange = RatingMatchers.build("range:C,A1");
		assertThat(MoodyRating.from("Aaa"), is(not(inRange)));
	}
	
	@Test
	public void shouldExtractToRange() {
		Matcher<MoodyRating> inRange = RatingMatchers.build("range:C,Aaa");
		assertThat(MoodyRating.from("Aaa"), is(inRange));
	}
	
	@Test
	public void shouldNotInRange() {
		Matcher<MoodyRating> inRange = RatingMatchers.build("range:C,Aaa");
		assertThat(MoodyRating.from("NR"), is(not(inRange)));
	}
}
