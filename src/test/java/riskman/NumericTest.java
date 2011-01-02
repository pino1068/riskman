package riskman;
import static riskman.numeric.Numeric.*;

import static junit.framework.Assert.*;

import org.junit.*;

public class NumericTest {
	@Test
	public void shouldBeCreated() {
		assertEquals(1.234d,$("1.234").toDouble());
	}
	
	@Test 
	public void shouldCreateFromInteger() {
		assertEquals(1.d,$(1).toDouble());
	}

	@Test
	public void shouldBeOne() {
		assertEquals($(1),$one());
	}

	@Test
	public void shouldBeZero() {
		assertEquals($(0),$zero());
	}
	
	@Test 
	public void shouldBeInfinite() {
		assertTrue($(1).divide($(0)).isInfinite());
		assertEquals($(1),$(0).divide($(0)));
		assertEquals($(1),$zero().divide($zero()));
		assertFalse($(0).divide($(0)).isInfinite());
	}
	
	@Test 
	public void shouldBeInverted() {
		assertEquals($zero(),$inf().invert());
		assertEquals($inf(),$zero().invert());
		assertEquals($one(),$one().invert());
		assertEquals($(2),$one().divide($(2)).invert());
	}
	
	@Test 
	public void shouldUseToString() {
		assertEquals("2.00000", $(2.000).toString());
		assertEquals("infinite oo", $(2).divide($(0)).toString());
	}
	
	@Test 
	public void shouldCompare() {
		assertEquals($(2), $(2.000));
		assertEquals($(2.000000000000000), $(2.000));
	}

	@Test 
	public void shouldAdd() {
		assertEquals($(4), $(2).add($(2)));
		assertEquals($(5), $(3).add($(2)));
	}

	@Test
	public void shouldSubstract() {
		assertEquals($(-1), $(2).subtract($(3)));
		assertEquals($(0), $(2).minus($(2)));
	}

	@Test 
	public void shouldDivide() {
		assertEquals($(1), $(2).divide($(2)));
	}

	@Test 
	public void shouldMultiply() {
		assertEquals($(4), $(2).multiply($(2)));
		assertEquals($(6), $(2).multiply($(3)));
		assertEquals($infinite(), $(2).multiply($infinite()));
	}

	@Test 
	public void shouldMultiplyInfiniteByZero() {
		assertEquals($(1), $inf().multiply($(0)));
	}

	@Test 
	public void shouldAdd2NumericsAfterDivision() {
		assertEquals($(4), $(40).divide($(20)).add($(2)));
	}

	@Test 
	public void shouldDevide4By3() {
		assertEquals($(4.0/3.0), $(4).divide($(3)));
	}

	@Test 
	public void shouldAdd2Rationals() {
		assertEquals($(1), $(3).divide($(4)).add($(1).divide($(4))));
		assertEquals($(5.0/3), $(4).divide($(3)).add($(1).divide($(3))));
	}

	@Test 
	public void shouldZeroBeEqual() {
		assertEquals($(30).divide($(0)), $(3).divide($(0)));
		assertEquals($(0).divide($(10)), $(0).divide($(10000)));
	}

	@Test 
	public void shouldInfiniteBeAdded() {
		assertEquals($infinite(), $(1).add($infinite()));
	}

	@Test 
	public void shouldInfiniteBeDivided() {
		assertEquals($(0), $(10).divide($infinite()));
		assertEquals($infinite(), $infinite().divide($(1)));
	}

	@Test 
	public void shouldInfiniteBeDividedByZero() {
		assertEquals($inf(), $infinite().divide($(0)));
	}

	@Test 
	public void shouldDivideByZeroBeInfinite() {
		assertEquals($(0), $(1).divide($infinite()));
	}

	@Test
	public void shouldApproximateDividingBigFromSmall() {
		assertEquals($(0.0000001), $(0.00000000001));
		assertEquals($(0.0000001), $(0.00000000001).divide($(99999999999d)));
	}

	@Test
	public void shouldNegate() {
		assertEquals($(-1), $(1).negate());
		assertEquals($(-56.0), $(56).negate());
	}

	@Test
	public void shouldBeGreaterThan() {
		assertTrue($(2).greaterThan($(1)));
		assertTrue($(2).greaterThan($(-5)));
		assertTrue($(2).gt($(-5)));
	}

	@Test
	public void shouldBeLowerThan() {
		assertTrue($(1).lowerThan($(2)));
		assertTrue($(-5).lowerThan($(2)));
		assertTrue($(-5).lt($(2)));
	}

	@Test
	public void shouldBeLowerOrGreaterThanOrEqual() {
		assertTrue($(2).ge($(-5)));
		assertTrue($(2).ge($(2)));
		assertTrue($(-5).le($(2)));
		assertTrue($(-5).le($(-5)));
	}
}
