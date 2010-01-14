package ch.siagile.finance;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

public class RangeCheck extends Check {

	private final BigDecimal from;
	private final BigDecimal to;

	public RangeCheck(double from, double to) {
		this.from = BigDecimal.valueOf(from);
		this.to = BigDecimal.valueOf(to);
		checkValues();
	}

	private void checkValues() {
		if(this.from.compareTo(this.to) >= 0)
			throw new InvalidParameterException("Invalid parameter values: from="+this.from.doubleValue()+" and to:"+this.to.doubleValue());
	}

	public RangeCheck(String value) {
		this(fromPercent(value), toPercent(value));
	}

	private static double toPercent(String limit) {
		return Double.valueOf(limit.split("%|,")[2])/100;
	}

	private static double fromPercent(String limit){
		return Double.valueOf(limit.split("%")[0])/100;
	}

	@Override
	public boolean check(Ratio value) {
		return value.isGreaterTheOrEqualsTo(this.from) && value.isLowerOrEqualsTo(this.to) ;
	}

}
