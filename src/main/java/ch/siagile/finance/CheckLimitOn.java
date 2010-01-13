package ch.siagile.finance;

import org.hamcrest.*;

public class CheckLimitOn {

	private final boolean valid;

	public CheckLimitOn(Positions positions, Matcher<Position> matcher, Check limit) {
		Positions selectedPortfolio = positions.selectBasedOn(matcher);
		Ratio ratio = selectedPortfolio.value().divideBy(positions.value());
		this.valid = limit.check(ratio);

	}

	public boolean isValid() {
		return valid;
	}

}
