package ch.siagile.finance;
 

import static java.text.MessageFormat.*;

import java.security.*;

public class CheckBuilder {

	private final String definition;

	public CheckBuilder(String definition) {
		this.definition = definition;
	}

	public Check build() {
		String type = extractType(definition);
		String value = extractValue(definition);
		if(isMax(type))
			return new MaxCheck(percent(value));
		if(isMin(type))
			return new MinCheck(percent(value));
		if(isEquals(type))
			return new EqualsCheck(percent(value));
		if(isRange(type))
			return new RangeCheck(value);
		throw new InvalidParameterException(format("invalid check definition set: {0}",this.definition));
	}

	private double percent(String limit) {
		return Double.valueOf(extractPercent(limit))/100;
	}

	private static String extractPercent(String limit) {
		return limit.split("%")[0];
	}
	/*
	 * i.e. "range:<value>%"
	 */
	private boolean isRange(String type) {
		return isType(type, "range");
	}

	/*
	 * i.e. "eq:<value>%" or "equals:<value>%" "equal:<value>%" or "=<value>%"
	 */
	private boolean isEquals(String type) {
		return 	isType(type, "eq") 		|| 
				isType(type, "equals") 	|| 
				isType(type, "equal") 	|| 
				isType(type, "equalsTo") 	|| 
				isType(type, "=");
	}

	private boolean isType(String type, String target) {
		return target.equalsIgnoreCase(type);
	}

	/*
	 * i.e. "min:<value>%"
	 */
	private boolean isMin(String type) {
		return isType(type, "min");
	}

	/*
	 * i.e. "max:<value>%"
	 */
	private boolean isMax(String type) {
		return isType(type, "max");
	}

	private String extractValue(String string) {
		return string.split(":")[1];
	}

	private static String extractType(String string) {
		return string.split(":")[0];
	}
}
