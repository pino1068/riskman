package ch.siagile.finance;


import static ch.siagile.finance.fixtures.Fixtures.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.*;


public class PositionMatcherTestCase {

	@Test
	public void shouldMatchOneGeneralEquityPosition() {
		Matcher<Position> isEquity = new IsEquityMatcher<Position>();
		Position position = IBM(5);

		assertThat(position, isEquity);
	}

	@Test
	public void shouldMatchOneSpecificEquityPosition() {
		Matcher<Position> isIBMEquity = new IsSpecificEquityMatcher<Position>("IBM");
		Position position = IBM(5);

		assertThat(position, isIBMEquity);
	}

	@Test
	public void shouldNotMatchOneAccountPosition() {
		Matcher<Position> isIBMEquity = allOf(new IsSpecificEquityMatcher<Position>("IBM"), new IsEquityMatcher<Position>());
		Position position = account("adsf", CHF(300));

		assertThat(position, not(isIBMEquity));
	}

	@Test
	public void shouldNotMatchOneOtherEquityPosition() {
		Matcher<Position> isOracleEquity = new IsSpecificEquityMatcher<Position>("Oracle");
		Position position = IBM(300);

		assertThat(position, not(isOracleEquity));
	}

	@Test
	public void shouldMatchOnePositionInCHF() {
		Matcher<Position> inCHF = new IsCurrencyMatcher<Position>("CHF");
		Position position = IBM(5);

		assertThat(position, inCHF);
	}

	@Test
	public void shouldNotMatchOnePositionInUSD() {
		Matcher<Position> inUSD = new IsCurrencyMatcher<Position>("USD");
		Position position = IBM(5);

		assertThat(position, not(inUSD));
	}

	@Test
	public void shouldMatchOnePositionInUSDOrCHF() {
		Matcher<Position> USDorCHF = new IsCurrenciesMatcher<Position>("USD", "CHF");
		Position position = IBM(5);

		assertThat(position, USDorCHF);
	}

	@Test
	public void shouldMatchOnePositionInUSDOrCHFOrEUR() {
		Matcher<Position> USDorCHForEUR = new IsCurrenciesMatcher<Position>("USD", "CHF", "EUR");
		Position position = IBM(5);

		assertThat(position, USDorCHForEUR);
	}

	@Test
	public void shouldNotMatchOnePositionInUSDOrEUR() {
		Matcher<Position> USDorEUR = new IsCurrenciesMatcher<Position>("USD", "EUR");
		Position position = IBM(5);

		assertThat(position, not(USDorEUR));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldMatchOnePositionInORtypeCondition() {
		Matcher<Position> equityOrCHF = anyOf(currency("CHF"), equity());
		Position position = IBM(5);

		assertThat(position, equityOrCHF);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldNotMatchOneUSDPositionInANDtypeCondition() {
		Matcher<Position> equityInUSD = allOf(currency("USD"), equity());
		Position position = IBM(5);

		assertThat(position, not(equityInUSD));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldMatchOneCHFPositionInANDtypeCondition() {
		Matcher<Position> equityInCHF = allOf(currency("CHF"), equity());
		Position position = IBM(5);

		assertThat(position, equityInCHF);
	}

	private Matcher<Position> equity() {
		return new IsEquityMatcher<Position>();
	}

	private Matcher<Position> currency(String currency) {
		return new IsCurrencyMatcher<Position>(currency);
	}

	@Test
	public void shouldThrowExceptionWhenCurrenciesNotAllowed() {
		try {
			new IsCurrenciesMatcher("equity");
			fail("expected exception for wrong currencies");
		} catch (Exception expectedBehaivor) {
		}
	}
}