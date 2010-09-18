package riskman;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import riskman.instrument.*;
import riskman.location.*;

public class LocationTest {
	
	@Test  
	public void shouldLocateAnEquityUsingString() {
		Equity equity = new Equity("2","IBM");
		Location.from(equity).locateIn("USA");
		
		Location location = Location.from(equity);
		
		assertThat(location.area(), is(equalTo(Area.from("USA"))));
	}

	@Test  
	public void shouldLocateAnEquityInAnArea() {
		Equity equity = new Equity("2","USA");
		Location.from(equity).locateIn(Area.from("USA"));
		
		assertThat(Location.from(equity).area(), is(Area.from("USA")));
	}
	
	@Test  
	public void shouldLocateABondInAnArea() {
		Bond bond = Bond.from("2","EIB 02", "UE");
		
		assertThat(Location.from(bond).area(), is(Area.from("UE")));
	}
	
}
