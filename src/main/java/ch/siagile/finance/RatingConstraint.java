package ch.siagile.finance;

import org.hamcrest.*;

public class RatingConstraint extends Constraint {

	private final Matcher<Position> matcher;

	public RatingConstraint(String ratings, String check) {
		super(Check.from(check));
		matcher = RatingMatchers.build(ratings);
	}

	@Override
	protected Matcher<Position> matcher() {
		return matcher;
	}

}
