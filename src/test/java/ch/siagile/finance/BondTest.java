package ch.siagile.finance;


import static ch.siagile.finance.matcher.IsRatingInMatcher.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.instrument.rating.*;
import ch.siagile.finance.location.*;

public class BondTest {

	MoodyRating AAA = MoodyRatings.find("Aaa");
	MoodyRating A1 = MoodyRatings.find("A1");
	MoodyRating C = MoodyRatings.find("C");

	@Test
	public void shouldBondBeLocalted() {
		Bond bond = Bond.from("EIB 02", "UE");

		assertTrue(bond.isLocated("UE"));
	}

	@Test
	public void shouldBondInRatingRange() {
		Bond bondAaa = Bond.from("EIB 02", MoodyRatings.find("Aaa"), Area.from("UE"));

		assertThat(bondAaa, is(ratingIn(A1, AAA)));
	}

	@Test
	public void shouldBondNotInRatingRange() {
		Bond bondAaa = Bond.from("EIB 02", MoodyRatings.find("Aaa"), Area.from("UE"));

		assertThat(bondAaa, is(not(ratingIn(C, A1))));
	}
}