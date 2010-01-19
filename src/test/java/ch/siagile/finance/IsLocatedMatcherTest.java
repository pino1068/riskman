package ch.siagile.finance;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;
import static ch.siagile.finance.IsLocatedMatcher.*;

public class IsLocatedMatcherTest {
	
	@Test
	public void shouldMatchBond() {
		Bond bond = Bond.from("name", "UE");
		
		assertThat(bond, is(located("UE")));
	}
	
	@Test
	public void shouldMatchBondPostion() {
		BondPosition position = new BondPosition(Bond.from("name", "UE"), Money.from(100, "CHF"), "100%");
		
		assertThat(position, is(located("UE")));
	}
	
	@Test
	public void shouldMatchSomeLocations() {
		Bond bond = Bond.from("name", "UE");
		BondPosition position = new BondPosition(Bond.from("name", "UE"), Money.from(100, "CHF"), "100%");

		assertThat(bond, is(located("UE","USA")));
		assertThat(position, is(located("UE","USA")));
	}
	
	@Test
	public void shouldNotMatchALocation() {
		BondPosition position = new BondPosition(Bond.from("name", "UE"), Money.from(100, "CHF"), "100%");

		assertThat(position, is(not(located("USA"))));
	}
	
	@Test public void shouldNotMatchBondAreaNull() {
		BondPosition position = new BondPosition(Bond.from("name", null), Money.from(100, "CHF"), "100%");

		assertThat(position, is(not(located("USA"))));
	}
	
}
