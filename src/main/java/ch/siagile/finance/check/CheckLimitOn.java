package ch.siagile.finance.check;

import ch.siagile.finance.constraint.*;
import ch.siagile.finance.position.*;

public class CheckLimitOn {

	private final boolean valid;

	public CheckLimitOn(Positions positions, Filter filter, Check check) {
		valid = positions.isEmpty()?false:check.check(positions.ratioOn(filter));
	}

	public boolean isValid() {
		return valid;
	}

}