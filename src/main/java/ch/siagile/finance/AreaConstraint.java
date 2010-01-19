package ch.siagile.finance;

import org.hamcrest.*;

public class AreaConstraint extends Constraint {

	private IsLocatedMatcher<Position> matcher;

	public AreaConstraint(String check, String... area) {
		super(Check.from(check));
		matcher = new IsLocatedMatcher<Position>(area);
	}

	@Override
	protected Matcher<Position> matcher() {
		return matcher;
	}

}
