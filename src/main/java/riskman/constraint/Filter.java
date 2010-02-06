package riskman.constraint;

import org.hamcrest.*;

import riskman.matcher.*;
import riskman.position.*;

public class Filter extends BaseMatcher<Position> {

	private Matcher<Position> matcher;

	public Filter(Matcher<Position> matcher) {
		this.matcher = matcher;
	}
	public boolean matches(Object arg0) {
		return matcher.matches(arg0);
	}

	public void describeTo(Description arg0) {
		matcher.describeTo(arg0);
	}
	public static Filter from(String definition) {
		return FilterBuilder.from(definition);
	}

}
