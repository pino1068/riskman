package riskman.check;

import riskman.money.*;

public class MinCheck  extends SingleCheck{

	public MinCheck(double value) {
		super(value);
	}

	public boolean check(Ratio value) {
		return value.isGreaterTheOrEqualsTo(value());
	}

	@Override
	public String toString() {
		return "min is "+super.toString();
	}

}
