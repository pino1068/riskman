package ch.siagile.finance;

public class EquityConstraint extends Constraint {

	public EquityConstraint(String check) {
		super(Check.from(check));
	}

	@Override
	protected IsEquityMatcher<Position> matcher() {
		return new IsEquityMatcher<Position>();
	}

}
