package ch.siagile.finance;

import org.hamcrest.*;

public class AreaConstraint extends Constraint {

	private String area;

	public AreaConstraint(String area, String check) {
		super(Check.from(check));
		this.area = area;
	}

	@Override
	protected Matcher<Position> matcher() {
		return new IsLocatedMatcher(area);
	}

}
