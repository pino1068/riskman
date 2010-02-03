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
	private final static Rating A1 = MoodyRating.from("A1");

	private Position bond;
	private Position ibm;
	private Position account;
	private Position oracle;

	private FilterBuilder matcherParser;
	private Positions positions;
	private List<Position> filter;

	@Before
	public void setUp() {
		positions = new Positions();
		account = account("name", CHF(30));
		ibm = equity("IBM", 1, CHF(30));
		oracle = equity("ORCL", 1, USD(30));
		bond = bond(Bond.from("name", A1, "USA"), USD(40), "100%");
		def(account);
		def(ibm);
		def(bond);
		def(oracle);
		matcherParser = new FilterBuilder();
	}

	@Test 
	public void shouldFilterBondAndEquity() {
		filter("equity +bond");
		check(hasItem(bond));
		check(hasItem(ibm));
		check(hasNotItem(account));
		check(hasItem(oracle));
	}

	@Test 
	public void shouldFilterBondAndEquityWtihSpaces() {
		filter("equity + bond");
		check(hasItem(bond));
		check(hasItem(ibm));
		check(hasNotItem(account));
		check(hasItem(oracle));
	}

	@Test 
	public void shouldFilterBonds() {
		filter("bond");
		check(hasItem(bond));
	}

	@Test 
	public void shouldFilterByCHF() {
		filter("CHF");
		check(hasItems(account, ibm));
		check(not(hasItems(bond)));
	}

	@Test 
	public void shouldFilterByUSD() {
		filter("USD");
		check(hasItems(bond));
		check(not(hasItems(account, ibm)));
	}

	@Test 
	public void shouldFilterByUSDAndCHF() {
		filter("USD + CHF");
		check(hasItems(bond, account, ibm));
	}

	@Test 
	public void shouldFilterByUSDAndCHFWithComma() {
		filter("USD,CHF");
		check(hasItems(bond, account, ibm));
	}

	@Test 
	public void shouldFilterByUSDAndCHFWithCommaAndSpace() {
		filter("USD , CHF");
		check(hasItems(bond, account, ibm));
	}

	@Test 
	public void shouldSpecificEquity() {
		filter("equity:IBM");
		check(hasItem(ibm));
		check(hasNotItem(bond));
		check(hasNotItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterInUSA() {
		filter("USA");
		check(hasNotItem(ibm));
		check(hasItem(bond));
		check(hasNotItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterInUSAorUE() {
		filter("USA,UE");
		check(hasItem(ibm));
		check(hasItem(bond));
		check(hasNotItem(account));
		check(hasItem(oracle));
	}

	@Test 
	public void shouldInvalidFilter() {
		filter("don't know what to do...");
	}

	@Test 
	public void shouldFilterEquityUSD() {
		filter("equity:USD");
		check(hasNotItem(ibm));
		check(hasNotItem(bond));
		check(hasNotItem(account));
		check(hasItem(oracle));
	}

	@Test 
	public void shouldFilterByOwner() {
		filter("owner:bondOwner,equityOwner");
		check(hasItem(ibm));
		check(hasItem(bond));
		check(hasNotItem(account));
		check(hasItem(oracle));
	}

	@Test 
	public void shouldFilterBondUSA() {
		filter("bond:USA");
		check(hasNotItem(ibm));
		check(hasItem(bond));
		check(hasNotItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterUSAorCHF() {
		filter("CHF+USA");
		check(hasItem(ibm));
		check(hasItem(bond));
		check(hasItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterUSAandCHF() {
		filter("CHF:USA");
		check(hasNotItem(ibm));
		check(hasNotItem(bond));
		check(hasNotItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterUSAandCHFWithWhiteSpaces() {
		filter("CHF : USA");
		check(hasNotItem(ibm));
		check(hasNotItem(bond));
		check(hasNotItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterByRating() {
		filter("range:B1,Aaa");
		check(hasNotItem(ibm));
		check(hasItem(bond));
		check(hasNotItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterIBMorOracle() {
		filter("IBM, ORCL");
		check(hasItem(ibm));
		check(hasNotItem(bond));
		check(hasNotItem(account));
		check(hasItem(oracle));
	}

	private void check(Matcher<Iterable<Position>> matcher) {
		assertThat(filter, matcher);
	}

	private Positions filterPositions(String definition) {
		return positions.select(matcherParser.build(definition));
	}

	private FilterTest def(Position position) {
		positions.add(position);
		return this;
	}

	private void filter(String string) {
		filter = filtered(string);
	}

	private List<Position> filtered(String definition) {
		List<Position> result = new ArrayList<Position>();
		for (Position position : filterPositions(definition))
			result.add(position);
		return result;
	}

	@Test 
	public void shouldFilterAccount() {
		filter("account");
		Position bond2 = bond;
		check(hasNotItem(bond2));
		check(hasNotItem(ibm));
		check(hasItem(account));
		check(hasNotItem(oracle));
	}

	@Test 
	public void shouldFilterAccountAndEquity() {
		filter("account,equity");
		Position bond2 = bond;
		check(hasNotItem(bond2));
		check(hasItem(ibm));
		check(hasItem(account));
		check(hasItem(oracle));
	}

	@Test 
	public void shouldFilterAccountAndBondWithPlus() {
		filter("account+bond");
		Position bond2 = bond;
		check(hasItem(bond2));
		check(hasNotItem(ibm));
		check(hasItem(account));
		check(hasNotItem(oracle));
	}

	private static Matcher<Iterable<Position>> hasNotItem(Position position) {
		return not(hasItem(position));
	}
}
