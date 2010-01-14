package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.CHF;
import static ch.siagile.finance.fixtures.Fixtures.IBM;
import static ch.siagile.finance.fixtures.Fixtures.account;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.junit.Test;


public class PositionMatcherTestCase {
	
	@Test
	public void shouldMatchOneGeneralEquityPosition() {
		Matcher<Position> isEquity = new IsEquityMatcher();
		Position position = IBM(5);
		
		assertThat(position, isEquity);
	}

	@Test
	public void shouldMatchOneSpecificEquityPosition() {
		Matcher<Position> isIBMEquity = new IsSpecificEquityMatcher("IBM");
		Position position = IBM(5);
		
		
		assertThat(position, isIBMEquity);
	}

	@Test
	public void shouldNotMatchOneAccountPosition() {
		Matcher<Position> isIBMEquity = new IsSpecificEquityMatcher("IBM");
		Position position = account("adsf",CHF(300));
		
		assertThat(position, not(isIBMEquity));
	}

	@Test
	public void shouldNotMatchOneOtherEquityPosition() {
		Matcher<Position> isOracleEquity = new IsSpecificEquityMatcher("Oracle");
		Position position = IBM(300);
		
		assertThat(position, not(isOracleEquity));
	}

	@Test
	public void shouldMatchOnePositionInCHF() {
		Matcher<Position> inCHF = new IsCurrencyMatcher("CHF");
		Position position = IBM(5);
		
		assertThat(position, inCHF);
	}

	@Test
	public void shouldNotMatchOnePositionInUSD() {
		Matcher<Position> inUSD = new IsCurrencyMatcher("USD");
		Position position = IBM(5);
		
		assertThat(position, not(inUSD));
	}

	@Test
	public void shouldMatchOnePositionInUSDOrCHF() {
		Matcher<Position> USDorCHF = new IsCurrenciesMatcher("USD", "CHF");
		Position position = IBM(5);
		
		assertThat(position, USDorCHF);
	}

	@Test
	public void shouldMatchOnePositionInUSDOrCHFOrEUR() {
		Matcher<Position> USDorCHForEUR = new IsCurrenciesMatcher("USD", "CHF", "EUR");
		Position position = IBM(5);
		
		assertThat(position, USDorCHForEUR);
	}
	
	@Test
	public void shouldNotMatchOnePositionInUSDOrEUR() {
		Matcher<Position> USDorEUR = new IsCurrenciesMatcher("USD", "EUR");
		Position position = IBM(5);
		
		assertThat(position, not(USDorEUR));
	}
	
	@Test
	public void shouldMatchOnePositionInORtypeCondition() {
		Matcher<Position> equityOrCHF = anyOf(currency("CHF"), equity());
		Position position = IBM(5);
		
		assertThat(position, equityOrCHF);
	}
	
	@Test
	public void shouldNotMatchOneUSDPositionInANDtypeCondition() {
		Matcher<Position> equityInUSD = allOf(currency("USD"), equity());
		Position position = IBM(5);
		
		assertThat(position, not(equityInUSD));
	}
	
	@Test
	public void shouldMatchOneCHFPositionInANDtypeCondition() {
		Matcher<Position> equityInCHF = allOf(currency("CHF"), equity());
		Position position = IBM(5);
		
		assertThat(position, equityInCHF);
	}

	private Matcher<Position> equity() {
		return new IsEquityMatcher();
	}

	private Matcher<Position> currency(String currency) {
		return new IsCurrencyMatcher(currency);
	}
	
//
//	@Test
//	public void shouldMatchPositionsMax30Percent() {
//		
//	}
}
