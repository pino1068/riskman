package ch.siagile.finance;

import org.hamcrest.*;

public class EquitySpecificConstraint extends Constraint {

	private final Matcher matcher;

	public EquitySpecificConstraint(String check, String... equities) {
		super(Check.from(check));
		matcher = new IsSpecificEquityMatcher(equities);
	}

	@Override
	protected Matcher matcher() {
		return matcher;
	}
}
