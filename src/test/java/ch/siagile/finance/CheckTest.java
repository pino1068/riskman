package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class CheckTest {

	private Check check;

	@Test
	public void shouldEquityContaint() {
		Check check = new MaxCheck(1.0 / 2.0);

		assertTrue(check.check(percentRatio(10)));
		assertTrue(check.check(percentRatio(20)));
		assertFalse(check.check(percentRatio(51)));
	}

	@Test
	public void shouldBeMax() {
		assertThat(Check.from("max: 20%"), is(instanceOf(MaxCheck.class)));
	}

	@Test
	public void shouldCreateMax20Percent() {
		Check check = Check.from("max: 20%");

		assertRatio(check, 10);
		assertRatio(check, 20);
		assertNotRatio(check, 30);
	}

	@Test
	public void shouldEqBeEqualsCheck() {
		assertThat(Check.from("eq: 20%"), is(instanceOf(EqualsCheck.class)));
	}

	@Test
	public void shouldEqualBeEqualsCheck() {
		assertThat(Check.from("equal: 20%"), is(instanceOf(EqualsCheck.class)));
	}

	@Test
	public void shouldEq20Percent() {
		Check check = Check.from("eq: 20%");

		assertNotRatio(check, 10);
		assertRatio(check, 20);
		assertNotRatio(check, 30);
	}

	@Test
	public void shouldEqualsBeEqualsCheck() {
		assertThat(Check.from("equals: 20%"), is(instanceOf(EqualsCheck.class)));
	}

	@Test
	public void shouldEqualsSignBeEqualsCheck() {
		assertThat(Check.from("=: 20%"), is(instanceOf(EqualsCheck.class)));
	}

	@Test
	public void shouldEquals20Percent() {
		Check check = Check.from("equals: 20%");

		assertNotRatio(check, 10);
		assertRatio(check, 20);
		assertNotRatio(check, 30);
	}

	@Test
	public void shouldEqual20Percent() {
		Check check = Check.from("equal: 20%");

		assertNotRatio(check, 10);
		assertRatio(check, 20);
		assertNotRatio(check, 30);
	}

	@Test
	public void shouldEqualsTo20Percent() {
		Check check = Check.from("equalsTo: 20%");

		assertNotRatio(check, 10);
		assertRatio(check, 20);
		assertNotRatio(check, 30);
	}

	@Test
	public void shouldEqualsSymbol20Percent() {
		Check check = Check.from("=: 20%");

		assertNotRatio(check, 10);
		assertRatio(check, 20);
		assertNotRatio(check, 30);
	}

	@Test
	public void shouldMinBeMinCheck() {
		assertThat(Check.from("min: 20%"), is(instanceOf(MinCheck.class)));
	}

	@Test
	public void shouldCreateMin20Percent() {
		Check check = Check.from("min: 20%");

		assertRatio(check, 30);
		assertRatio(check, 20);
		assertNotRatio(check, 10);
	}

	private void assertNotRatio(Check check, int percent3) {
		assertFalse(check.check(percentRatio(percent3)));
	}

	private void assertRatio(Check check, int percent) {
		assertTrue(check.check(percentRatio(percent)));
	}

	@Test
	public void shouldRangteBeRangeCheck() {
		assertThat(Check.from("range: 20%, 29.997%"), is(instanceOf(RangeCheck.class)));
	}

	@Test
	public void shouldCreateRangeFrom20To30Percent() {
		Check check = Check.from("range: 20%, 29.997%");

		truePercent(check, 25);
		truePercent(check, 20);
		truePercent(check, 29.9d);
		falsePercent(check, 10);
		falsePercent(check, 40);
	}

	@Test
	public void shouldCheckInvalidRange() {
		try {
			Check.from("range: 30%, 20%");
			fail("expecting to get an InvalidParameterException here");
		} catch (Exception expectedBehaivor) {
		}
	}

	private void falsePercent(Check check, double percent) {
		assertFalse(check.check(percentRatio(percent)));
	}

	private void truePercent(Check check, double percent) {
		assertTrue(check.check(percentRatio(percent)));
	}

	private Ratio percentRatio(double percent) {
		return Ratio.from(CHF(percent), CHF(100));
	}

}
