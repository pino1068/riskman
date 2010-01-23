package ch.siagile.finance;

import static ch.siagile.finance.money.Money.*;

import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.money.*;


public class MoneyTest {
	
	@Test
	public void shouldSumTwoMoney() {
		Money one = Money.from(1,"CHF");
		Money two = Money.from(2, "CHF");

		Money three = one.plus(two);

		assertEquals(Money.from(3, "CHF"), three);
	}
	
//	@Test
//	public void shouldConvertUSDInCHF() {
//		Money dollars = Money.from(100, "USD");
//		Exchange exchange = exchange();
//		
//		Money euros = exchange.change(dollars).to("EUR");
//		
//		assertEquals(Money.from(60, "EUR"), euros);
//	}

	@Test
	public void shouldConvertUSDInCHF() {
		Money dollars = Money.from(100, "USD");
		ExchangeRate rate = ExchangeRate.from(USD(1),CHF(1.1));
		
		Money francs = rate.change(dollars);
		
		assertEquals(Money.from(110, "CHF"), francs);
	}
	
	@Test
	public void shouldSumDifferentCurrenciesUsingDefaultExchangeRate() {
		Money chf100 = Money.from(100, "CHF");
		Money usd100 = Money.from(100, "USD");
		
		assertEquals(Money.from(210, "CHF"), chf100.plus(usd100));
	}

//	@Test
//	public void shouldLoadCurrencyPairs() {
//		Exchange exchange = Exchange.load();
//		
//		assertThat(exchange.rates().size(), is(30));
//	}
}
