package riskman.matcher;

import org.hamcrest.*;

import riskman.position.*;

public class IsEquityMatcher<T> extends BaseMatcher<T> {

	public void describeTo(Description description) {
		description.appendText("matches when the position is an entity position");
	}

	public boolean matches(Object item) {
		if(EquityPosition.class.isInstance(item)) return true;
		return false;
	}


}
