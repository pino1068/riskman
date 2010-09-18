
package riskman.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static riskman.fixtures.Fixtures.*;
import static riskman.money.Money.*;

import org.junit.*;

import riskman.Split;
import riskman.instrument.Bond;
import riskman.position.*;
import riskman.splitter.*;

public class SplitterTest {

	private Positions positions;

	@Before
	public void setUp() {
		positions = new Positions(IBM(CHF(1000)), UBS(USD(2000)), account(
				"stipendio", CHF(5000)), bond(Bond.from("2","bond1", "USA"),
				CHF(4000), "100%"));
	}
	
	@Test  
	public void shouldCreateASplit() {
		Split split = new Split();
		split.add("CHF", positions);
		split.add("USD", new Positions());
		
		assertThat(split.size(), is(2));
		hasGroup(split, "CHF");
		hasGroup(split, "USD");
		assertThat(split.positions("CHF").size(), is(4));
		assertThat(split.positions("USD").size(), is(0));
	}

	@Test 
	public void shouldSplitPerCurrency() {
		Splitter splitter = SplitterBuilder.from("currency");
		
		Split split = splitter.split(positions);
		
		assertThat(split.size(), is(2));
		hasGroup(split, "CHF");
		hasGroup(split, "USD");
		assertThat(split.positions("CHF").size(), is(3));
		assertThat(split.positions("USD").size(), is(1));
	}
	
	@Test  
	public void shouldSplitPerSinglePosition() {
		Splitter splitter = SplitterBuilder.from("position");
		
		Split split = splitter.split(positions);
		
		assertThat(split.size(), is(4));
	}
	
	@Test @Ignore
	public void shouldSplitPerPositionType() {
		Splitter splitter = SplitterBuilder.from("type");
		
		Split split = splitter.split(positions);
		
		assertThat(split.size(), is(3));
		
		hasGroup(split, "Account");
		hasGroup(split, "Bond");
		hasGroup(split, "Equity");

	}
	
	@Test  
	public void shouldSplitByOwner() {
		Splitter splitter  = SplitterBuilder.from("owner");
		
		Split split = splitter.split(positions);
		assertThat(split.size(), is(3));
		hasGroup(split, "accountOwner");
		hasGroup(split, "bondOwner");
		hasGroup(split, "equityOwner");
	}

	private void hasGroup(Split split, String string) {
		assertThat(split.groups(), hasItem((Object)string));
	}

	private void hasGroup(Split split, Class<? extends Position> class1) {
		assertThat(split.groups(), hasItem((Object)class1));
	}
}
