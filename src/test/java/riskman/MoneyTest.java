package riskman;

import static riskman.money.Money.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.money.*;


public class MoneyTest {
	
	private Money CHF100;
	private Money USD100;
	
	@Before
	public void setUp(){
		ExchangeRates.add(ExchangeRate.rateFrom(Money.CHF(1),Money.CHF(1)));
		ExchangeRates.add(ExchangeRate.rateFrom(Money.USD(1),Money.CHF(1.1)));
		ExchangeRates.add(ExchangeRate.rateFrom(Money.EUR(1),Money.CHF(1.6)));
	}

	@Test 
	public void shouldSumTwoMoney() {
		Money one = Money.money(1,"CHF");
		Money two = Money.money(2, "CHF");

		Money three = one.plus(two);

		assertEquals(Money.money(3, "CHF"), three);
	}

	@Test 
	public void shouldRateConvertUSDInCHF() {
		Money dollars = Money.money(100, "USD");
		ExchangeRate rate = ExchangeRate.rateFrom(USD(1),CHF(1.1));
		
		Money francs = rate.change(dollars);
		
		assertEquals(Money.money(110, "CHF"), francs);
	}
	
	@Test 
	public void shouldSumDifferentCurrenciesUsingDefaultExchangeRate() {
		CHF100 = Money.CHF(100);
		USD100 = Money.USD(100);
		
		final Money sum = Money.CHF(210);
		
		assertEquals(sum, CHF100.plus(USD100));
	}
}
