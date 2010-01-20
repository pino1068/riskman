package ch.siagile.finance.constraint;

import static org.hamcrest.Matchers.*;

import org.hamcrest.*;

import ch.siagile.finance.check.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;


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
