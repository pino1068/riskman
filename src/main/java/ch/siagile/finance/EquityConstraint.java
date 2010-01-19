package ch.siagile.finance;

import static org.hamcrest.Matchers.*;

import org.hamcrest.*;


public class EquityConstraint extends Constraint {

	private Matcher<Position> matcher;

	@SuppressWarnings("unchecked")
	public EquityConstraint(String check, String... equities) {
		super(Check.from(check));
		matcher = allOf(new IsEquityMatcher<Position>(), new IsSpecificEquityMatcher<Position>(equities));
	}

	public EquityConstraint(String check) {
		super(Check.from(check));
		matcher = new IsEquityMatcher<Position>();
	}

	@Override
	protected Matcher<Position> matcher() {
		return matcher;
	}
}
