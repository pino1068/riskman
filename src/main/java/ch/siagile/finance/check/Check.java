package ch.siagile.finance.check;

import ch.siagile.finance.money.*;

public abstract class Check {

	public abstract boolean check(Ratio value);

	public static Check from(String limit) {
		return new CheckBuilder(limit).build();
	}

}