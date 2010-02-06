package riskman.check;

import riskman.money.*;

public class EqualsCheck extends SingleCheck {
	
	public EqualsCheck(double bigDecimal) {
		super(bigDecimal);
	}

	public boolean check(Ratio value) {
		return value.isEqualsTo(value());
	}

	@Override
	public String toString() {
		return "equal to"+super.toString();
	}

}
