package riskman.check;

import riskman.constraint.*;
import riskman.position.*;

public class CheckLimitOn {

	private final boolean valid;

	public CheckLimitOn(Positions positions, Filter filter, Check check) {
		valid = positions.isEmpty()?false:check.check(positions.ratioOn(filter));
	}

	public boolean isValid() {
		return valid;
	}

}