package ch.siagile.finance.app;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static ch.siagile.finance.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.hamcrest.*;
import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.instrument.rating.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class FilterTest {

	private Position bond;
	private Position ibm_equity;
	private Position account;
	private MatcherBuilder matcherParser;
	private Positions positions;
	private List<Position> filter;
	private Position oracle_equity;

	@Before
	public void setUp() {
		positions = new Positions();
		account = account("name", CHF(30));
		ibm_equity = equity("IBM", 1, CHF(30));
		oracle_equity = equity("ORCL", 1, USD(30));
		bond = bond(Bond.from("name", MoodyRating.from("A1"),"USA"), USD(40), "100%");
		matcherParser = new MatcherBuilder();
	}
	
	@Test 
	public void shouldFilterBondAndEquity() {
		def(account);def(ibm_equity);def(bond);
		filter("equity bond");
		check(hasItems(bond, ibm_equity));
		check(not(hasItems(account,bond)));
	}
	
	@Test 
	public void shouldFilterBonds() {
		def(account);def(ibm_equity);def(bond);
		filter("bond");
		check(hasItem(bond));
	}
	
	@Test 
	public void shouldFilterByCHF() {
		def(account); def(ibm_equity);def(bond);
		filter("CHF");
		check(hasItems(account,ibm_equity));
		check(not(hasItems(bond)));
	}
	
	@Test 
	public void shouldFilterByUSD() {
		def(account); def(ibm_equity);def(bond);
		filter("USD");
		check(hasItems(bond));
		check(not(hasItems(account,ibm_equity)));
	}
	
	@Test @Ignore
	public void shouldFilterByUSDAndCHF() {
		def(account); def(ibm_equity);def(bond);
		filter("USD + CHF");
		check(hasItems(bond,account,ibm_equity));
	}
	
	@Test 
	public void shouldFilterByUSDAndCHFWithComma() {
		def(account); def(ibm_equity);def(bond);
		filter("USD,CHF");
		check(hasItems(bond,account,ibm_equity));
	}
	
	@Test 
	public void shouldFilterByUSDAndCHFWithCommaAndSpace() {
		def(account); def(ibm_equity);def(bond);
		filter("USD , CHF");
		check(hasItems(bond,account,ibm_equity));
	}

	@Test
	public void shouldSpecificEquity() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("equity:IBM");
		check(hasItem(ibm_equity));
		check(not(hasItem(bond)));
		check(not(hasItem(account)));
		check(not(hasItem(oracle_equity)));
	}	
	
	@Test 
	public void shouldFilterInUSA() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("USA");
		check(not(hasItem(ibm_equity)));
		check(hasItem(bond));
		check(not(hasItem(account)));
		check(not(hasItem(oracle_equity)));
	}
	
	@Test 
	public void shouldFilterInUSAorUE() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("USA,UE");
		check(hasItem(ibm_equity));
		check(hasItem(bond));
		check(not(hasItem(account)));
		check(hasItem(oracle_equity));
	}
	
	@Test 
	public void shouldInvalidFilter() {
		filter("don't know what to do...");
	}
	
	@Test
	public void shouldFilterEquityUSD() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("equity:USD");
		check(not(hasItem(ibm_equity)));
		check(not(hasItem(bond)));
		check(not(hasItem(account)));
		check(hasItem(oracle_equity));
	}
	
	@Test
	public void shouldFilterByOwner() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("owner#bondOwner,equityOwner");
		check(hasItem(ibm_equity));
		check(hasItem(bond));
		check(not(hasItem(account)));
		check(hasItem(oracle_equity));
	}
	
	@Test
	public void shouldFilterBondUSA() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("bond:USA");
		check(not(hasItem(ibm_equity)));
		check(hasItem(bond));
		check(not(hasItem(account)));
		check(not(hasItem(oracle_equity)));
	}
	
	@Test
	public void shouldFilterUSAorCHF() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("CHF USA");
		check(hasItem(ibm_equity));
		check(hasItem(bond));
		check(hasItem(account));
		check(not(hasItem(oracle_equity)));
	}
	
	@Test
	public void shouldFilterUSAandCHF() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("CHF:USA");
		check(not(hasItem(ibm_equity)));
		check(not(hasItem(bond)));
		check(not(hasItem(account)));
		check(not(hasItem(oracle_equity)));
	}
	
	@Test
	public void shouldFilterUSAandCHFWithWhiteSpaces() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("CHF : USA");
		check(not(hasItem(ibm_equity)));
		check(not(hasItem(bond)));
		check(not(hasItem(account)));
		check(not(hasItem(oracle_equity)));
	}
	
	@Test 
	public void shouldFilterByRating() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("rating=min.B1");
		check(not(hasItem(ibm_equity)));
		check(hasItem(bond));
		check(not(hasItem(account)));
		check(not(hasItem(oracle_equity)));
	}
	
	@Test 
	public void shouldFilterIBMorOracle() {
		def(account).
		def(ibm_equity).
		def(bond).
		def(oracle_equity);
		filter("IBM,ORCL");
		check(hasItem(ibm_equity));
		check(not(hasItem(bond)));
		check(not(hasItem(account)));
		check(hasItem(oracle_equity));
	}

	private void check(Matcher<Iterable<Position>> matcher) {
		assertThat(filter, matcher);
	}

	private List<Position> filtered(String definition) {
		List<Position> result = new ArrayList<Position>();
		for (Position position : parseAndSelect(definition)) 
			result.add(position);
		return result;
	}

	private Positions parseAndSelect(String definition) {
		return positions.select(matcherParser.build(definition));
	}

	private FilterTest def(Position position) {
		positions.add(position);
		return this;
	}

	private void filter(String string) {
		filter = filtered(string);
	}
}
