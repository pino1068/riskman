package ch.siagile.finance;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class PercentTest {

	private static final Percent PERCENT20 = Percent.from("20%");

	@Test 
	public void shouldPercentBeBuild() {
		Percent percent = Percent.from("30%");
		
		assertEquals(Double.valueOf(30), percent.value());
	}
	
	@Test 
	public void shouldMultiplyWithMoney() {
		Money amount = Money.CHF(100);
		
		Money multiplied = amount.times(PERCENT20);
		
		assertThat(multiplied,is(equalTo(Money.CHF(20))));
	}
	
	@Test 
	public void shouldSumPercentToMoney() {
		Money CHF100 = Money.CHF(100);
		
		Money plus20Percent = CHF100.plus(PERCENT20);
		
		assertThat(plus20Percent, is(Money.CHF(120)));
	}
	
	@Test 
	public void shouldEmptyStringBeZeroPercent() {
		Percent percent = Percent.from("");
		
		assertEquals(Double.valueOf(0), percent.value());
	}
	
}
