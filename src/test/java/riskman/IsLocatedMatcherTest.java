package riskman;

import static riskman.matcher.IsLocatedMatcher.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.instrument.*;
import riskman.money.*;
import riskman.position.*;

public class IsLocatedMatcherTest {
	
	private Bond bond;
	private BondPosition position;

	@Before
	public void setUp(){
		bond = Bond.from("2","name", "UE");
		position = new BondPosition(bond, Money.CHF(100), hundredPercent());
	}
	
	@Test 
	public void shouldMatchBond() {
		assertThat(bond, is(located("UE")));
	}
	
	@Test 
	public void shouldMatchBondPostion() {
		assertThat(position, is(located("UE")));
	}
	
	@Test 
	public void shouldMatchSomeLocations() {
		assertThat(bond, 		is(located("UE","USA")));
		assertThat(position, 	is(located("UE","USA")));
	}
	
	@Test 
	public void shouldNotMatchALocation() {
		assertThat(position, is(not(located("USA"))));
	}
	
	@Test  public void shouldNotMatchBondAreaNull() {
		position = bondPositionWithNullArea();

		assertThat(position, is(not(located("USA"))));
	}

	private BondPosition bondPositionWithNullArea() {
		return new BondPosition(Bond.from("2","name", null), Money.CHF(100), hundredPercent());
	}

	private Percent hundredPercent() {
		return Percent.from("100%");
	}
}
