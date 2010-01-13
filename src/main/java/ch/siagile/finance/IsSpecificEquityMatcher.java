package ch.siagile.finance;

import org.hamcrest.*;

public class IsSpecificEquityMatcher extends BaseMatcher<Position> {

	private final String equity;
	private IsEquityMatcher equityMatcher = new IsEquityMatcher();

	public IsSpecificEquityMatcher(String equity) {
		this.equity = equity;
	}

	public void describeTo(Description description) {
		description.appendText("exptected specified equity: ");
		description.appendValue(this.equity);
	}

	public boolean matches(Object item) {
		if (!equityMatcher.matches(item))
			return false;
		EquityPosition position = (EquityPosition) item;
		return position.isEquity(equity);
	}

}
