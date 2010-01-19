package ch.siagile.finance.constraint;

import org.hamcrest.*;

import ch.siagile.finance.check.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

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
