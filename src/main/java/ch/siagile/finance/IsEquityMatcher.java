package ch.siagile.finance;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class IsEquityMatcher extends BaseMatcher<Position> {

	public void describeTo(Description description) {
		description.appendText("matches when the position is an entity position");
	}

	public boolean matches(Object item) {
		if(EquityPosition.class.isInstance(item)) return true;
		return false;
	}


}
