package ch.siagile.finance.constraint;


import static java.text.MessageFormat.*;

import org.hamcrest.*;

import ch.siagile.finance.check.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class Constraint {

	private Check check;
	private final Matcher<Position> matcher;

	public Constraint(Check check) {
		matcher = null;
		this.check = check;
	}

	public Constraint(Matcher<Position> matcher, Check check) {
		this.matcher = matcher;
		this.check = check;
	}

	public boolean checkLimitOn(Positions positions) {
		return new CheckLimitOn(positions, matcher(), check).isValid();
	}

	protected Matcher<Position> matcher(){
		return matcher;
	}

	@Override
	public String toString() {
		return format("{0} with limit: {1}", matcher(), check);
	}

	public Positions filter(Positions positions) {
		return positions.select(matcher());
	}

	public static Constraint from(String constraint, String check) {
		return new Constraint(MatchersBuilder.from(constraint), Check.from(check)) ;
	}
}
