package riskman.numeric;

import org.junit.Test;

public class NumericPerformanceTest {

	@Test(timeout=7000)
	public void shouldAddAndMultiplyOnNumeric() {
		Numeric a = Numeric._zero();
		for (int j = 0; j < 100000; j++) 
			a = a.add(Numeric._(j)).times(5);
	}

	@Test(timeout=7000)
	public void shouldAddAndMultiplyOnFast() {
		FastNumeric a = FastNumeric._zero();
		for (int j = 0; j < 1000000; j++) 
			a = a.add(FastNumeric._(j)).times(5);
	}

	@Test(timeout=7000)
	public void shouldAddAndMultiplyOnVeryFast() {
		VeryFastNumeric a = VeryFastNumeric._zero();
		for (int j = 0; j < 1000000; j++) 
			a = a.add(VeryFastNumeric._(j)).times(5);
	}

	@Test(timeout=2)
	public void shouldAddAndMultiplyWithDoubleNoWrap() {
		double a = 0;
		for (int j = 0; j < 1000000; j++) 
			a = (a+j)*5;
	}

}
