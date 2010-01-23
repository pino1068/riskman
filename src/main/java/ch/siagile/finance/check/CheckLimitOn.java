package ch.siagile.finance.check;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public class CheckLimitOn {

	private final boolean valid;

	public CheckLimitOn(Positions allPositions, Matcher<Position> matcher, Check check) {
		valid = check.check(allPositions.ratioOn(matcher));
	}

	public boolean isValid() {
		return valid;
	}

}