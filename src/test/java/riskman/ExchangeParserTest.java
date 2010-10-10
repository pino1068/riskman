package riskman;

import static org.junit.Assert.assertEquals;
import static riskman.money.Money.money;

import org.junit.Before;
import org.junit.Test;

import riskman.money.ExchangeRates;
import riskman.money.Money;
import riskman.parser.ExchangeRateParser;

public class ExchangeParserTest {
	
	private Money CHF100 = Money.CHF(100);
	private Money USD100 = Money.USD(100);
	private Money EUR100 = Money.EUR(100);
	
	@Before
	public void setUp(){
		ExchangeRates.use(ExchangeRateParser.load("Cambi_out.csv"));
	}

	@Test
	public void shouldConvertMoney() {
		assertEquals(money(105.39, "CHF"), 	CHF100.change(USD100));
		assertEquals(money(100/1.0539, "USD"), 	USD100.change(CHF100));
		assertEquals(money(100*1.42392429, "CHF"), 	CHF100.change(EUR100));
	}
}
