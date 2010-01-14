package ch.siagile.finance;

import static org.junit.Assert.*;
import static ch.siagile.finance.fixtures.Fixtures.CHF;

import org.junit.Test;


public class LimitTest {
	
	@Test
	public void shouldEquityContaint() {
		Check test = new MAXCheck(0.5);
 		
		assertTrue(test.check(percentRatio(10)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(51)));
	}
	
	@Test
	public void shouldCreateMax20Percent() {
		Check test = Check.from("max: 20%");
		
		assertEquals(MAXCheck.class, test.getClass());
		assertTrue(test.check(percentRatio(10)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(30)));
	}
	
	@Test
	public void shouldEq20Percent() {
		Check test = Check.from("eq: 20%");
		
		assertEquals(EqualsCheck.class, test.getClass());
		assertFalse(test.check(percentRatio(10)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(30)));
	}
	
	@Test
	public void shouldEquals20Percent() {
		Check test = Check.from("equals: 20%");
		
		assertEquals(EqualsCheck.class, test.getClass());
		assertFalse(test.check(percentRatio(10)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(30)));
	}
	
	@Test
	public void shouldEqual20Percent() {
		Check test = Check.from("equal: 20%");
		
		assertEquals(EqualsCheck.class, test.getClass());
		assertFalse(test.check(percentRatio(10)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(30)));
	}
	
	@Test
	public void shouldEqualsTo20Percent() {
		Check test = Check.from("equalsTo: 20%");
		
		assertEquals(EqualsCheck.class, test.getClass());
		assertFalse(test.check(percentRatio(10)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(30)));
	}
	
	@Test
	public void shouldEqualsSymbol20Percent() {
		Check test = Check.from("=: 20%");
		
		assertEquals(EqualsCheck.class, test.getClass());
		assertFalse(test.check(percentRatio(10)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(30)));
	}
	
	@Test
	public void shouldCreateMin20Percent() {
		Check test = Check.from("min:20%");
		
		assertEquals(MINCheck.class, test.getClass());
		assertTrue(test.check(percentRatio(30)));
		assertTrue(test.check(percentRatio(20)));
		assertFalse(test.check(percentRatio(10)));
	}
	
	@Test
	public void shouldCreateRangeFrom20To30Percent(){
		Check test = Check.from("range:20%, 29.997%");
		
		assertEquals(RangeCheck.class, test.getClass());
		truePercent(test, 25);
		truePercent(test, 20);
		truePercent(test, 29.9d);
		falsePercent(test, 10);
		falsePercent(test, 40);
	}
	
	@Test
	public void shouldCheckInvalidRange(){
		try {
			Check.from("range: 30%, 20%");
			fail("expecting to get an InvalidParameterException here");
		} catch (Exception e) {
			//it's ok!
		}
	}

 	private void falsePercent(Check test, double percent) {
		assertFalse(test.check(percentRatio(percent)));
	}

	private void truePercent(Check test, double percent) {
		assertTrue(test.check(percentRatio(percent)));
	}

	private Ratio percentRatio(double percent) {
		return Ratio.from(CHF(percent), CHF(100));
	}

}
