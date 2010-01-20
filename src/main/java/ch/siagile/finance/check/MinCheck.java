package ch.siagile.finance.check;

import ch.siagile.finance.base.*;

public class MinCheck  extends SingleCheck{

	public MinCheck(double value) {
		super(value);
	}

	public boolean check(Ratio value) {
		return value.isGreaterTheOrEqualsTo(value());
	}

}
