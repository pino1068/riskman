package ch.siagile.finance.check;

import ch.siagile.finance.constraint.*;
import ch.siagile.finance.position.*;

public class CheckLimitOn {

	private final boolean valid;

	public CheckLimitOn(Positions allPositions, Filter matcher, Check check) {
		valid = check.check(allPositions.ratioOn(matcher));
	}

	public boolean isValid() {
		return valid;
	}

}