package ch.siagile.finance;

import static ch.siagile.finance.IsLocatedMatcher.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.fixtures.*;
import ch.siagile.finance.instrument.*;

public class IsLocatedMatcherTest {

	private final static Bond bond = Fixtures.bond("GOOG", "UE");
	private final static Money CHF100 = Money.CHF(100);
	private BondPosition position = new BondPosition(bond, CHF100, "100%");

	@Test
	public void shouldMatchBond() {
		assertThat(bond, is(locatedIn("UE")));
	}

	@Test
	public void shouldMatchBondPostion() {
		assertThat(position, is(locatedIn("UE")));
	}

	@Test
	public void shouldMatchSomeLocations() {
		assertThat(bond, is(locatedIn("UE,USA")));
		assertThat(position, is(locatedIn("UE,USA")));
	}

	@Test
	public void shouldNotMatchALocation() {
		assertThat(position, is(not(locatedIn("USA"))));
	}

	@Test
	public void shouldNotMatchBondAreaNull() {
		position = new BondPosition(Bond.from("name"), CHF100, "100%");

		assertThat(position, is(not(locatedIn("USA"))));
	}

}
