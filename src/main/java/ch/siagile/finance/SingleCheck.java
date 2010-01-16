package ch.siagile.finance;

import java.math.*;

public abstract class SingleCheck extends Check {

	private Percent percent;

	public SingleCheck(Percent percent) {
		this.percent = percent;
	}

	public BigDecimal value() {
		return percent.toBigDecimal();
	}

}
