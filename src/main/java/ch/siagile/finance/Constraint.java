package ch.siagile.finance;

import static java.text.MessageFormat.*;

import org.hamcrest.*;

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
}
