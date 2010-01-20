package ch.siagile.finance.check;

import static java.text.MessageFormat.*;

import java.security.*;

import ch.siagile.finance.money.*;

public class RangeCheck extends Check {

	private final Check from;
	private final Check to;

	public RangeCheck(double from, double to) {
		if (from > to)
			throw new InvalidParameterException(format("Invalid parameter values: from={0} and to:{1}", from, to));
		this.from = new MinCheck(from);
		this.to = new MaxCheck(to);
	}

	public RangeCheck(String value) {
		this(fromPercent(value), toPercent(value));
	}

	private static double toPercent(String limit) {
		return Double.valueOf(limit.split("%|,")[2]) / 100;
	}

	private static double fromPercent(String limit) {
		return Double.valueOf(limit.split("%")[0]) / 100;
	}

	@Override
	public boolean check(Ratio value) {
		return from.check(value) && to.check(value);
	}

}
