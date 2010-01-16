package ch.siagile.finance;

import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.location.*;

public class BondTest {
	
	@Test public void shouldBondBeLocalted() {
		Bond bond = Bond.from("EIB 02");
		Location.from(bond).locateIn("UE");
		
		assertTrue(bond.isLocatedIn("UE"));
	}
	
}
