package ch.siagile.finance;

import org.hamcrest.*;

public abstract class Constraint {
	private Check limit;

	public Constraint(Check limit) {
		this.limit = limit;
	}

	public boolean checkLimitOn(Positions positions) {
		return new CheckLimitOn(positions, matcher(), limit).isValid();
	}

	protected abstract Matcher<Position> matcher();

	@Override
	public String toString() {
		return matcher().toString() + " with limit: " + limit;
	}
}
