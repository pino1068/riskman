package ch.siagile.finance;

import org.hamcrest.*;

public class CheckLimitOn {

	private final boolean valid;

	public CheckLimitOn(Positions allPositions, Matcher<Position> matcher, Check check) {
		Positions selectedPositions = allPositions.select(matcher);
		Ratio ratio = selectedPositions.divideBy(allPositions);
		valid = check.check(ratio);
	}

	public boolean isValid() {
		return valid;
	}

}