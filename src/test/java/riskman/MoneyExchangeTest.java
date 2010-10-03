package riskman;

import static org.junit.Assert.*;
import static riskman.fixtures.Fixtures.*;

import static riskman.money.Money.*;

import org.junit.*;

import riskman.money.*;
import riskman.position.*;

public class MoneyExchangeTest {
	
	private Money CHF100 = Money.CHF(100);
	private Money USD100 = Money.USD(100);
	private Money EUR100 = Money.EUR(100);
	
	@Before
	public void setUp(){
		ExchangeRates.clear();
		ExchangeRates.addRate(ExchangeRate.rateFrom(CHF(1),CHF(1)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(USD(1),CHF(1.1)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(EUR(1),CHF(1.6)));
	}

	@Test 
	public void shouldConvertMoney() {
		assertEquals(money(110, "CHF"), 	CHF100.change(USD100));
		assertEquals(money(90.91, "USD"), 	USD100.change(CHF100));
		assertEquals(money(160, "CHF"), 	CHF100.change(EUR100));
	}

	@Test
	public void shouldSumUsingCrossCurrency() {
		assertEquals(money(100+100*1.6, "CHF"), CHF100.plus(EUR100));
		assertEquals(money(100/1.6+100, "EUR"), EUR100.plus(CHF100));
		assertEquals(money(100+100 *1.6/1.1, "USD"), USD100.plus(EUR100));
	}

	@Test
	public void shouldNoUseCrossRate() {
		ExchangeRates.addRate(ExchangeRate.rateFrom(EUR(2),USD(3)));
		assertEquals(money(100+100 *3/2, "USD"), USD100.plus(EUR100));
	}

	@Test 
	public void shouldRateConvertUSDInCHF() {
		Money dollars = money(100, "USD");
		ExchangeRate rate = ExchangeRate.rateFrom(USD(1),CHF(1.1));
		
		Money francs = rate.change(dollars);
		
		assertEquals(money(110, "CHF"), francs);
	}
	
	@Test 
	public void shouldSumDifferentCurrenciesUsingDefaultExchangeRate() {
		CHF100 = CHF(100);
		USD100 = USD(100);
		
		final Money sum = CHF(210);
		
		assertEquals(sum, CHF100.plus(USD100));
	}

	@Test
	public void shouldDivideExchangeRates(){
		ExchangeRate USDCHF = ExchangeRate.rateFrom(USD(3),CHF(3.3));
		ExchangeRate EURCHF = ExchangeRate.rateFrom(EUR(2),CHF(3.2));
		
		ExchangeRate crossUSDEUR = USDCHF.cross(EURCHF);

		assertTrue(crossUSDEUR.canChange("USD"));
		assertTrue(crossUSDEUR.canChange("EUR"));
		assertTrue(crossUSDEUR.canChange("USD", "EUR"));
		assertEquals(money(10* (1.1/1.6), "EUR"), 	crossUSDEUR.change(money(10, "USD")));
	}

	@Test
	public void shouldConvertPositionsInCHF(){
		Position eurPos = account("", EUR(50));
		Position usdPos = account("pippo", USD(500));
		Positions allMultiCurrency = new Positions(eurPos, usdPos);
		
		Money sum = CHF(0).plus(allMultiCurrency.value());
		
		assertEquals(CHF(550+80),sum);
	}

	@Test(expected=RuntimeException.class)
	public void shouldBreakWhenNoExchangeRates(){
		ExchangeRates.clear();
		Position eurPos = account("", EUR(50));
		Position usdPos = account("pippo", USD(500));
		Positions allMultiCurrency = new Positions(eurPos, usdPos);
		
		CHF(0).plus(allMultiCurrency.value());
	}
}
