package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.check.*;

public class CheckTest {

	private Check check;

	@Test
	public void shouldEquityContaint() {
		check = new MaxCheck(0.5);

		assertCheck(check, "10%");
		assertCheck(check, "20%");
		assertNotCheck(check, "51%");
	}

	@Test
	public void shouldBeMax() {
		assertThat(Check.from("max: 20%"), is(instanceOf(MaxCheck.class)));
	}

	@Test
	public void shouldCreateMax20Percent() {
		check = Check.from("max: 20%");

		assertCheck(check, "10%");
		assertCheck(check, "20%");
		assertNotCheck(check, "30%");
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
		check = Check.from("eq: 20%");

		assertNotCheck(check, "10%");
		assertCheck(check, "20%");
		assertNotCheck(check, "30%");
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
		check = Check.from("equals: 20%");

		assertNotCheck(check, "10%");
		assertCheck(check, "20%");
		assertNotCheck(check, "30%");
	}

	@Test
	public void shouldEqual20Percent() {
		check = Check.from("equal: 20%");

		assertNotCheck(check, "10%");
		assertCheck(check, "20%");
		assertNotCheck(check, "30%");
	}

	@Test
	public void shouldEqualsTo20Percent() {
		check = Check.from("equalsTo: 20%");

		assertNotCheck(check, "10%");
		assertCheck(check, "20%");
		assertNotCheck(check, "30%");
	}

	@Test
	public void shouldEqualsSymbol20Percent() {
		check = Check.from("=: 20%");

		assertNotCheck(check, "10%");
		assertCheck(check, "20%");
		assertNotCheck(check, "30%");
	}

	@Test
	public void shouldMinBeMinCheck() {
		assertThat(Check.from("min: 20%"), is(instanceOf(MinCheck.class)));
	}

	@Test
	public void shouldCreateMin20Percent() {
		check = Check.from("min: 20%");

		assertCheck(check, "30%");
		assertCheck(check, "20%");
		assertNotCheck(check, "10%");
	}

	private void assertNotCheck(Check check, String percent) {
		assertFalse(check.check(toRatio(doubleValue(percent))));
	}

	private void assertCheck(Check check, String percent) {
		assertTrue(check.check(toRatio(doubleValue(percent))));
	}

	private double doubleValue(String percent) {
		return Double.valueOf(percent.split("%")[0]);
	}

	@Test
	public void shouldRangteBeRangeCheck() {
		assertThat(Check.from("range: 20%, 29.997%"),
				is(instanceOf(RangeCheck.class)));
	}

	@Test
	public void shouldCreateRangeFrom20To30Percent() {
		check = Check.from("range: 20%, 29.997%");

		assertCheck(check, "25%");
		assertCheck(check, "20%");
		assertCheck(check, "29%.9d");
		assertNotCheck(check, "10%");
		assertNotCheck(check, "40%");
	}

	@Test
	public void shouldCheckInvalidRange() {
		try {
			Check.from("range: 30%, 20%");
			fail("expecting to get an InvalidParameterException here");
		} catch (Exception expectedBehaivor) {
		}
	}

	private Ratio toRatio(double percent) {
		return Ratio.from(CHF(percent), CHF(100));
	}

}
