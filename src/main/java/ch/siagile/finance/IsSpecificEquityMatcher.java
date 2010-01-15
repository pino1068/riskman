package ch.siagile.finance;

import org.hamcrest.*;

public class IsSpecificEquityMatcher<T> extends BaseMatcher<T> {

	private final String equity;
	private IsEquityMatcher<T> equityMatcher = new IsEquityMatcher<T>();

	public IsSpecificEquityMatcher(String equity) {
		this.equity = equity;
	}

	public void describeTo(Description description) {
		description.appendText("exptected specified equity: ");
		description.appendValue(equity);
	}

	public boolean matches(Object item) {
		if(!equityMatcher.matches(item)) return false;
		EquityPosition position = (EquityPosition) item;
		return position.isEquity(equity);
	}
	

}
 