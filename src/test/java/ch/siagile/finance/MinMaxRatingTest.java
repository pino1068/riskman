package ch.siagile.finance;

import static ch.siagile.finance.IsInMatcher.*;
import static ch.siagile.finance.IsMaxMatcher.*;
import static ch.siagile.finance.IsMinMatcher.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.instrument.rating.*;

public class MinMaxRatingTest {

	MoodyRating A1 = MoodyRatings.find("A1");
	MoodyRating C = MoodyRatings.find("C");
	MoodyRating AAA = MoodyRatings.find("Aaa");

	@Test
	public void shouldAAAIsMinOrEqualToA1() {
		assertThat(AAA, is(min(A1)));
	}

	@Test
	public void shouldA1IsMinOrEqualThanA1() {
		assertThat(A1, is(min(A1)));
	}

	@Test
	public void shouldCIsNotMinOrEqualThanA1() {
		assertThat(C, is(not(min(A1))));
	}

	@Test
	public void shouldAAAIsNotMaxOrEqualToA1() {
		assertThat(AAA, is(not(max(A1))));
	}

	@Test
	public void shouldCIsMaxOrEqualToA1() {
		assertThat(C, is(max(A1)));
	}

	@Test
	public void shouldA1IsMaxOrEqualToA1() {
		assertThat(A1, is(max(A1)));
	}

	@Test
	public void shouldA1IsInRangeCAndAAA() {
		assertThat(A1, is(ratingIn(C, AAA)));
	}
	
}
