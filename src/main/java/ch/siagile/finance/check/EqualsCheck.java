package ch.siagile.finance.check;

import ch.siagile.finance.base.*;

public class EqualsCheck extends SingleCheck {
	
	public EqualsCheck(double bigDecimal) {
		super(bigDecimal);
	}

	public boolean check(Ratio value) {
		return value.isEqualsTo(value());
	}


}
