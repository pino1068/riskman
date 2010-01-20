package ch.siagile.finance;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.location.*;

public class LocationTest {
	
	@Test 
	public void shouldLocateAnEquityUsingString() {
		Equity equity = new Equity("IBM");
		Location.from(equity).locateIn("USA");
		
		Location location = Location.from(equity);
		
		assertThat(location.area(), is(equalTo(Area.from("USA"))));
	}

	@Test 
	public void shouldLocateAnEquityInAnArea() {
		Equity equity = new Equity("USA");
		Location.from(equity).locateIn(Area.from("USA"));
		
		assertThat(Location.from(equity).area(), is(Area.from("USA")));
	}
	
	@Test 
	public void shouldLocateABondInAnArea() {
		Bond bond = Bond.from("EIB 02", "UE");
		
		assertThat(Location.from(bond).area(), is(Area.from("UE")));
	}
	
}
