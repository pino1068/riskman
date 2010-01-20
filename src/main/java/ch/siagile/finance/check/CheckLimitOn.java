package ch.siagile.finance.check;

import org.hamcrest.*;

import ch.siagile.finance.base.*;
import ch.siagile.finance.position.*;

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