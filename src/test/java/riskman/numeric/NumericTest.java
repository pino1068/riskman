package riskman.numeric;

import static riskman.numeric.Numeric.*;
import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.junit.*;

import riskman.numeric.Numeric;

public class NumericTest {
	
	
	@Test 
	public void shouldxxxx() {
		double a = 4.000000000000000001/3;
		double b = a*3;
		
		assertEquals(4.0, b);
	}
	
	@Test 
	@Ignore("cause we must round for dividing")
	public void shouldBigDecimal() {
		final BigDecimal three = new BigDecimal("3.0");
		
		BigDecimal a = new BigDecimal("4.000000000000000001").divide(three);
		BigDecimal b = a.multiply(three);
		
		System.out.println(b);
		assertEquals(new BigDecimal("4.0"), b);
	}
	
	@Test @Ignore("cause the identity checks are available at compile time")
	public void shouldDoubleIdenticalExpressions() {
//		assertTrue(4.0d == 4.0000000000000001d);
//		assertTrue(12345678901.234567d == 12345678901.2345678d);
//		assertTrue(12345678901234.5678d == 12345678901234.56789d);
//		assertTrue(12345678901234567.d == 12345678901234567.8d);
	}


	@Test
	public void shouldDoublePrintItWrong() {
		assertEquals("8000001.0", 8000001.0 + "");
		Double a = 80000.01;
		Double b = 100.0;

		assertFalse((8000001.0 + "").equals((a * b) + ""));
	}

	@Test
	public void shouldDoubleCalculateItWrong() {
		Double a = 80000.01;
		Double b = 100.0;

		double c = a * b / b;
		assertEquals(a * a * a, c * c * c);
	}

	@Test
	public void shouldDoubleCalculateItWrongWithLongDecimals() {
		Double a = 80000.00000000000000001;
		Double b = 100.0;

		assertEquals(8000000.000000000000001, a * b);
	}

	@Test
	public void shouldBeCreated() {
		assertEquals(1.234d, _("1.234").toDouble());
	}

	@Test
	public void shouldCreateFromInteger() {
		assertEquals(1.d, _(1).toDouble());
	}

	@Test
	public void shouldBeOne() {
		assertEquals(_(1), _one());
	}

	@Test
	public void shouldBeZero() {
		assertEquals(_(0), _zero());
	}

	@Test
	public void shouldBeInfinite() {
		assertTrue(_(1).divide(_(0)).isInfinite());
		assertEquals(_(1), _(0).divide(_(0)));
		assertEquals(_(1), _zero().divide(_zero()));
		assertFalse(_(0).divide(_(0)).isInfinite());
	}

	@Test
	public void shouldBeInverted() {
		assertEquals(_zero(), _inf().invert());
		assertEquals(_inf(), _zero().invert());
		assertEquals(_one(), _one().invert());
		assertEquals(_(2), _one().divide(_(2)).invert());
	}

	@Test
	public void shouldUseToString() {
		assertEquals("2.0", _(2.000).toString());
		assertEquals("infinite oo", _(2).divide(_(0)).toString());
	}

	@Test
	public void shouldCompare() {
		assertEquals(_(2), _(2.000));
		assertEquals(_(2.000000000000000), _(2.000));
	}

	@Test
	public void shouldAdd() {
		assertEquals(_(4), _(2).add(_(2)));
		assertEquals(_(5), _(3).add(_(2)));
	}

	@Test
	public void shouldSubstract() {
		assertEquals(_(-1), _(2).subtract(_(3)));
		assertEquals(_(0), _(2).minus(_(2)));
	}

	@Test
	public void shouldDivide() {
		assertEquals(_(1), _(2).divide(_(2)));
		assertEquals(_(2), _(4).divide(_(2)));
		assertEquals(_(2), _(4).div(_(2)));
	}

	@Test
	public void shouldMultiply() {
		assertEquals(_(4), _(2).multiply(_(2)));
		assertEquals(_(6), _(2).multiply(_(3)));
		assertEquals(_(6), _(2).mul(_(3)));
		assertEquals(_(6), _(2).times(3));
		assertEquals(_infinite(), _(2).multiply(_infinite()));
	}

	@Test
	public void shouldMultiplyInfiniteByZero() {
		assertEquals(_(1), _inf().multiply(_(0)));
	}

	@Test
	public void shouldAdd2NumericsAfterDivision() {
		assertEquals(_(4), _(40).divide(_(20)).add(_(2)));
	}

	@Test
	public void shouldInvertAndDivide4By3() {
		assertThat(_(3).divide(_(4)), is(_(4).divide(_(3)).invert()));
		assertThat(_(4.0 / 3.0), is(not(_(4).divide(_(3)))));
		assertThat(_(4.0 / 3.0), is(not(_(3.0 / 4.0).invert())));
	}

	@Test
	public void shouldScaleMaxTo15WithDoubles() {
		int scale = 15;

		Numeric a = _(4.0 / 3.0).scaleTo(scale);
		Numeric b = _(4).divide(_(3)).scaleTo(scale);

		assertThat(a, is(b));
	}

	@Test
	public void shouldFailTheScaleTo16WithDoubles() {
		int scale = 16;

		Numeric a = _(4.0 / 3.0).scaleTo(scale);
		Numeric b = _(4).divide(_(3)).scaleTo(scale);

		assertThat(a, is(not(b)));
	}

	@Test
	public void shouldAdd2Rationals() {
		assertEquals(_(1), _(3).divide(_(4)).add(_(1).divide(_(4))));
		assertEquals(_(5.0).divide(_(3)),
				_(4).divide(_(3)).add(_(1).divide(_(3))));
	}

	@Test
	public void shouldZeroBeEqual() {
		assertEquals(_(30).divide(_(0)), _(3).divide(_(0)));
		assertEquals(_(0).divide(_(10)), _(0).divide(_(10000)));
	}

	@Test
	public void shouldInfiniteBeAdded() {
		assertEquals(_infinite(), _(1).add(_infinite()));
	}

	@Test
	public void shouldInfiniteBeDivided() {
		assertEquals(_(0), _(10).divide(_infinite()));
		assertEquals(_infinite(), _infinite().divide(_(1)));
	}

	@Test
	public void shouldInfiniteBeDividedByZero() {
		assertEquals(_inf(), _infinite().divide(_(0)));
	}

	@Test
	public void shouldDivideByZeroBeInfinite() {
		assertEquals(_(0), _(1).divide(_infinite()));
	}

	@Test
	public void shouldNotIfNotScaled() {
		Numeric scaledTo7 = _(0.00000000001).scaleTo(7);

		assertThat(_(0.0000001), is(scaledTo7));
		assertThat(_(0.0000001).scaleTo(11), is(not(_(0.00000000001))));
	}

	@Test
	@Ignore
	public void shouldMatchWhenScale() {
		assertEquals(_(0.0000001), _(0.00000000001));
	}

	@Test
	@Ignore
	public void shouldNotMatchDividingBigFromSmall() {
		assertThat(_(0.0000001), is(not(_(0.00000000001)
				.divide(_(99999999999d)))));
	}

	@Test
	public void shouldNegate() {
		assertEquals(_(-1), _(1).negate());
		assertEquals(_(-56.0), _(56).negate());
	}

	@Test
	public void shouldBeGreaterThan() {
		assertTrue(_(2).greaterThan(_(1)));
		assertTrue(_(2).greaterThan(_(-5)));
		assertTrue(_(2).gt(_(-5)));
		assertFalse(_(2).gt(_(2)));
		assertFalse(_(-5).gt(_(2)));
	}

	@Test
	public void shouldBeLowerThan() {
		assertTrue(_(1).lowerThan(_(2)));
		assertTrue(_(-5).lowerThan(_(2)));
		assertTrue(_(-5).lt(_(2)));
		assertFalse(_(2).lt(_(2)));
		assertFalse(_(2).lt(_(-5)));
	}

	@Test
	public void shouldBeLowerOrGreaterThanOrEqual() {
		assertTrue(_(2).ge(_(-5)));
		assertTrue(_(2).ge(_(2)));
		assertTrue(_(-5).le(_(2)));
		assertTrue(_(-5).le(_(-5)));
		assertFalse(_(-5).ge(_(2)));
		assertFalse(_(2).le(_(-5)));
	}

}
