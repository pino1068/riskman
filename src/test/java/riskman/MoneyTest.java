package riskman;

import static riskman.money.Money.*;

import static org.junit.Assert.*;

import org.junit.*;

import riskman.money.*;


public class MoneyTest {
	
	@Test 
	public void shouldSumTwoMoney() {
		Money one = Money.money(1,"CHF");
		Money two = Money.money(2, "CHF");

		Money three = one.plus(two);

		assertEquals(Money.money(3, "CHF"), three);
	}

	@Test 
	public void shouldSumTwoMoneyInEUR() {
		Money eur120 = money(120, "EUR");
		Money eur10 = money( 10, "EUR");
		
		assertEquals(money(130, "EUR"), eur120.plus(eur10));
	}
}
