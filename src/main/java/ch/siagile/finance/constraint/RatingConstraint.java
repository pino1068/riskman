package ch.siagile.finance.constraint;

import org.hamcrest.*;

import ch.siagile.finance.check.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

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
