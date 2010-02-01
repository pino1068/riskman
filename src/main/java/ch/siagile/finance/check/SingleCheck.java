package ch.siagile.finance.check;

import java.math.*;

public abstract class SingleCheck extends Check {

	private BigDecimal value;

	public SingleCheck(double value) {
		this.value = BigDecimal.valueOf(value);
	}

	public BigDecimal value() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}


}
