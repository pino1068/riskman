package ch.siagile.finance;

import static org.hamcrest.Matchers.*;

import org.hamcrest.*;


public class EquityConstraint extends Constraint {

	private Matcher matcher;

	public EquityConstraint(String check, String... equities) {
		super(Check.from(check));
		matcher = allOf(new IsEquityMatcher(), new IsSpecificEquityMatcher(equities));
	}

	public EquityConstraint(String check) {
		super(Check.from(check));
		matcher = new IsEquityMatcher();
	}

	@Override
	protected Matcher matcher() {
		return matcher;
	}
}
