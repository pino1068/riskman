package ch.siagile.finance;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;

public class BondTest {
	
	@Test public void shouldBondBeLocalted() {
		Bond bond = Bond.from("EIB 02","UE");
		
		assertTrue(bond.isLocated("UE"));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void shouldMatchAnArea() {
		Bond bond = Bond.from("EIB 02", "UE");
		
		assertThat(bond, is(new AreaMatcher("UE")));
	}

}
