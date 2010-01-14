package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static org.junit.Assert.*;
import java.math.BigDecimal;

import org.junit.Test;


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
