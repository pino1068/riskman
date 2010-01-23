package ch.siagile.finance.constraint;

import org.hamcrest.*;

import ch.siagile.finance.*;
import ch.siagile.finance.check.*;
import ch.siagile.finance.position.*;

public class BondConstraint extends Constraint {

	public BondConstraint(String check) {
		super(Check.from(check));
	}

	@Override
	protected Matcher<Position> matcher() {
		return new IsBondMatcher<Position>();
	}

}
