package riskman;

import static riskman.money.Money.*;
import static org.junit.Assert.*;

import java.math.*;

import org.junit.*;

import riskman.money.*;


public class RatioTest {

	@Test 
	public void should1000Over1999IsLowerThen50Percent() {
		Money numerator = CHF(1000);
		Money denominator = CHF(1999);
		Ratio ratio = Ratio.from(numerator, denominator);

		assertTrue(ratio.isLowerOrEqualsTo(BigDecimal.valueOf(0.500250)));
		assertFalse(ratio.isLowerOrEqualsTo(BigDecimal.valueOf(0.500000)));
	}
}
