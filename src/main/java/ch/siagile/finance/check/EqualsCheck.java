package ch.siagile.finance.check;

import ch.siagile.finance.money.*;

public class EqualsCheck extends SingleCheck {
	
	public EqualsCheck(double bigDecimal) {
		super(bigDecimal);
	}

	public boolean check(Ratio value) {
		return value.isEqualsTo(value());
	}


}
