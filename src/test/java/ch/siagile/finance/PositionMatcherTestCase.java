package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static ch.siagile.finance.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;


public class PositionMatcherTestCase {

	@Test
	public void shouldMatchOneGeneralEquityPosition() {
		Matcher<Position> isEquity = new IsEquityMatcher<Position>();
		Position position = IBM(CHF(500));

		assertThat(position, isEquity);
	}

	@Test
	public void shouldMatchOneSpecificEquityPosition() {
		Matcher<Position> isIBMEquity = new IsSpecificEquityMatcher<Position>("IBM");
		Position position = IBM(CHF(500));

		assertThat(position, isIBMEquity);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void shouldNotMatchOneAccountPosition() {
		Matcher<Position> isIBMEquity = allOf(new IsSpecificEquityMatcher<Position>("IBM"), new IsEquityMatcher<Position>());
		Position position = account("adsf", CHF(300));

		assertThat(position, not(isIBMEquity));
	}

	@Test
	public void shouldNotMatchOneOtherEquityPosition() {
		Matcher<Position> isOracleEquity = new IsSpecificEquityMatcher<Position>("Oracle");
		Position position = IBM(CHF(300));

		assertThat(position, not(isOracleEquity));
	}

	@Test
	public void shouldMatchOnePositionInCHF() {
		Matcher<Position> inCHF = new IsCurrencyMatcher<Position>("CHF");
		Position position = IBM(CHF(500));

		assertThat(position, inCHF);
	}

	@Test
	public void shouldNotMatchOnePositionInUSD() {
		Matcher<Position> inUSD = new IsCurrencyMatcher<Position>("USD");
		Position position = IBM(CHF(500));

		assertThat(position, not(inUSD));
	}

	@Test
	public void shouldMatchOnePositionInUSDOrCHF() {
		Matcher<Position> USDorCHF = new IsCurrenciesMatcher<Position>("USD", "CHF");
		Position position = IBM(CHF(500));

		assertThat(position, USDorCHF);
	}

	@Test
	public void shouldMatchOnePositionInUSDOrCHFOrEUR() {
		Matcher<Position> USDorCHForEUR = new IsCurrenciesMatcher<Position>("USD", "CHF", "EUR");
		Position position = IBM(CHF(500));

		assertThat(position, USDorCHForEUR);
	}
	
	@Test 
	public void shouldMatchOnePositionOfEquityType() {
		Matcher<Position> equityMatcher = new IsTypeMatcher<Position>("equity");
		Position position = IBM(CHF(500));

		assertThat(position, equityMatcher);
	}
	@Test 
	public void shouldMatchOnePositionOfBondType() {
		Matcher<Position> bondMatcher = new IsTypeMatcher<Position>("bond");
		Position position = bond(Bond.from("IBM bond", "USA"),CHF(1000),"100%");

		assertThat(position, bondMatcher);
	}
	
	@Test
	public void shouldMatchOnePositionOfMultiTypes() {
		Matcher<Position> bondOrEquity = new IsTypeMatcher<Position>("bond", "equity");
		Position equity = IBM(CHF(500));
		Position bond = bond(Bond.from("IBM bond", "USA"),CHF(1000),"100%");

		assertThat(equity, bondOrEquity);
		assertThat(bond, bondOrEquity);
	}
	

	@Test
	public void shouldNotMatchOnePositionInUSDOrEUR() {
		Matcher<Position> USDorEUR = new IsCurrenciesMatcher<Position>("USD", "EUR");
		Position position = IBM(CHF(500));

		assertThat(position, not(USDorEUR));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void shouldMatchOnePositionInORtypeCondition() {
		Matcher<Position> equityOrCHF = anyOf(currency("CHF"), equity());
		Position position = IBM(CHF(500));

		assertThat(position, equityOrCHF);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void shouldNotMatchOneUSDPositionInANDtypeCondition() {
		Matcher<Position> equityInUSD = allOf(currency("USD"), equity());
		Position position = IBM(CHF(500));

		assertThat(position, not(equityInUSD));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void shouldMatchOneCHFPositionInANDtypeCondition() {
		Matcher<Position> equityInCHF = allOf(currency("CHF"), equity());
		Position position = IBM(CHF(500));

		assertThat(position, equityInCHF);
	}
	
	@Test 
	public void shouldMatchOnBondMatcher() {
		Matcher<Position> isBond = new IsBondMatcher<Position>();
		Position position = bond(Bond.from("a name", "anArea"), CHF(100), "100%");

		assertThat(position, isBond);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void shouldMatchOnePositionInUSDANDBond() {
		Matcher<Position> bondInUSD = allOf(currency("USD"), bondMatcher());
		Position position = bond(Bond.from("a name", "anArea"), USD(100), "100%");

		assertThat(position, bondInUSD);
	}
	

	private Matcher<Position> bondMatcher() {
		return new IsBondMatcher<Position>();
	}

	private Matcher<Position> equity() {
		return new IsEquityMatcher<Position>();
	}

	private Matcher<Position> currency(String currency) {
		return new IsCurrencyMatcher<Position>(currency);
	}

	@Test
	public void shouldWrongCurrenciesNotAllowed() {
		try {
			new IsCurrenciesMatcher<Position>("equity");
			fail("expected exception for wrong currencies");
		} catch (Exception expectedBehaivor) {
		}
	}
}