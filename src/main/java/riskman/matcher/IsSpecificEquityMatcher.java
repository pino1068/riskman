package riskman.matcher;

import org.hamcrest.*;

import riskman.position.*;

public class IsSpecificEquityMatcher<T> extends BaseMatcher<T> {

	private String[] equities;

	public IsSpecificEquityMatcher(String... equities) {
		this.equities = equities;
	}

	public void describeTo(Description description) {
		description.appendText("exptected specified equity: ");
		description.appendValue(equities);
	}

	public boolean matches(Object item) {
		if(!EquityPosition.class.isInstance(item)) return false;
		if(equities.length == 0) return true;
		EquityPosition position = (EquityPosition) item;
		for (String equity : equities) {
			if (position.isCalled(equity))
				return true;
		}
		return false;
	}

}
