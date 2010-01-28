package ch.siagile.finance;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.*;

import ch.siagile.finance.instrument.rating.*;
import ch.siagile.finance.matcher.*;


public class RatingMatchersTest {

	private RatingBuilder<Rating> builder;

	@Before
	public void setUp() {
		builder = new RatingBuilder<Rating>();
	}
	
	@Test
	public void shouldInRange() {
		assertThat(builder.build("range:C,Aaa"), is(instanceOf(IsRatingInMatcher.class)));		
	}

	@Test
	public void shouldMinA1() {
		assertThat(builder.build("min:A1"), is(instanceOf(IsMinMatcher.class)));		
	}

	@Test
	public void shouldMaxA2() {
		assertThat(builder.build("max:A2"), is(instanceOf(IsMaxMatcher.class)));		
	}

	@Test
	public void shouldThrowsExceptionIfNotRecognize() {
		try {
			builder.build("unknown:");
			fail("expected exception for unknown rating matchers");
		} catch (Exception expectedBehaivor) {
		}
	}
	
	@Test
	public void shouldIsMinA1() {
		Matcher<Rating> inMin = builder.buildWithType("min:A1");
		assertThat(MoodyRating.from("Aaa"), is(inMin));
	}
	
	@Test
	public void shouldAaa1IsMaxA1() {
		Matcher<Rating> inMax = builder.buildWithType("max:A1");
		assertThat(MoodyRating.from("A2"), is(inMax));
	}

	@Test
	public void shouldA3IsMinB1() {
		Matcher<Rating> inMin = builder.buildWithType("min:B1");
		assertThat(MoodyRating.from("A3"), is(inMin));
	}
	
	@Test
	public void shouldExtractFromRange() {
		Matcher<Rating> inRange = builder.buildWithType("range:C,Aaa");
		assertThat(MoodyRating.from("C"), is(inRange));
	}
	
	@Test
	public void shouldExtractFromRangeCandA1() {
		Matcher<Rating> inRange = builder.buildWithType("range:C,A1");
		assertThat(MoodyRating.from("Aaa"), is(not(inRange)));
	}
	
	@Test
	public void shouldExtractToRange() {
		Matcher<Rating> inRange = builder.buildWithType("range:C,Aaa");
		assertThat(MoodyRating.from("Aaa"), is(inRange));
	}
	
	@Test
	public void shouldExtractToRangeWithDot() {
		Matcher<Rating> inRange = builder.buildWithType("range#C,Aaa");
		assertThat(MoodyRating.from("Aaa"), is(inRange));
	}
		
	@Test
	public void shouldNotInRange() {
		Matcher<Rating> inRange = builder.buildWithType("range:C,Aaa");
		assertThat(MoodyRating.from("NR"), is(not(inRange)));
	}
	@Test
	public void shouldWorkWithDot() {
		Matcher<Rating> inRange = builder.buildWithType("range#C,Aaa");
		assertThat(MoodyRating.from("NR"), is(not(inRange)));
	}
}
