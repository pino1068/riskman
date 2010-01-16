package ch.siagile.finance;

import static java.text.MessageFormat.*;

import java.security.*;

public class RangeCheck extends Check {

	private final Check from;
	private final Check to;

	public RangeCheck(Percent from, Percent to) {
		if (from.isGreaterThen(to))
			throw new InvalidParameterException(format("Invalid parameter values: from={0} and to:{1}", from, to));
		this.from = new MinCheck(from);
		this.to = new MaxCheck(to);
	}

	public RangeCheck(String value) {
		this(fromPercent(value), toPercent(value));
	}

	private static Percent toPercent(String limit) {
		return Percent.from(limit.split(",")[1]);
	}

	private static Percent fromPercent(String limit) {
		return Percent.from(limit.split(",")[0]);
	}

	@Override
	public boolean check(Ratio value) {
		return from.check(value) && to.check(value);
	}

}
