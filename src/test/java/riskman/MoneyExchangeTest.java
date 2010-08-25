package riskman;

import static org.junit.Assert.*;

import org.junit.*;

import riskman.money.*;


public class MoneyExchangeTest {
	
	private Money CHF100 = Money.CHF(100);
	private Money USD100 = Money.USD(100);
	private Money EUR100 = Money.EUR(100);
	
	@Before
	public void setUp(){
		ExchangeRates.add(ExchangeRate.rateFrom(Money.CHF(1),Money.CHF(1)));
		ExchangeRates.add(ExchangeRate.rateFrom(Money.USD(1),Money.CHF(1.1)));
		ExchangeRates.add(ExchangeRate.rateFrom(Money.EUR(1),Money.CHF(1.6)));
	}

	@Test 
	public void shouldConvertMoney() {
		assertEquals(Money.money(110, "CHF"), 	CHF100.change(USD100));
		assertEquals(Money.money(90.91, "USD"), 	USD100.change(CHF100));
		assertEquals(Money.money(160, "CHF"), 	CHF100.change(EUR100));
	}
	
//	@Test 
//	public void shouldLoadCurrencyPairs() {
//		Exchange exchange = Exchange.load();
//		
//		assertThat(exchange.rates().size(), is(30));
//	}
}
