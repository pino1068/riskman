package ch.siagile.finance;

public class EquityConstraint extends Constraint {

	public EquityConstraint(String limit) {
		super(Check.from(limit));
	}

	@Override
	protected IsEquityMatcher matcher() {
		return new IsEquityMatcher();
	}

}
