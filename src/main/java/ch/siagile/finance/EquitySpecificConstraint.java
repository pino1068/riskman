package ch.siagile.finance;

public class EquitySpecificConstraint extends Constraint {

	private final String equity;

	public EquitySpecificConstraint(String equity, String limit) {
		super(Check.from(limit));
		this.equity = equity;
	}

	@Override
	protected IsSpecificEquityMatcher<Position> matcher() {
		return new IsSpecificEquityMatcher<Position>(equity);
	}
}
