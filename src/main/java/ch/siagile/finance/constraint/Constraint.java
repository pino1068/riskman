package ch.siagile.finance.constraint;


import static java.text.MessageFormat.*;

import org.hamcrest.*;

import ch.siagile.finance.check.*;
import ch.siagile.finance.position.*;

public abstract class Constraint {

	private Check check;

	public Constraint(Check check) {
		this.check = check;
	}

	public boolean checkLimitOn(Positions positions) {
		return new CheckLimitOn(positions, matcher(), check).isValid();
	}

	protected abstract Matcher<Position> matcher();

	@Override
	public String toString() {
		return format("{0} with limit: {1}", matcher(), check);
	}

	public Positions filter(Positions positions) {
		return positions.select(matcher());
	}
}
