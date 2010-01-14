package ch.siagile.finance;
 
import java.security.InvalidParameterException;

public class CheckParser {

	private final String definition;

	public CheckParser(String definition) {
		this.definition = definition;
	}

	public Check parse() {
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
		throw new InvalidParameterException("invalid check definition set: "+this.definition);
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
		return checkType(type, "range");
	}

	/*
	 * i.e. "eq:<value>%" or "equals:<value>%" "equal:<value>%" or "=<value>%"
	 */
	private boolean isEquals(String type) {
		return 	checkType(type, "eq") 		|| 
				checkType(type, "equals") 	|| 
				checkType(type, "equal") 	|| 
				checkType(type, "equalsTo") 	|| 
				checkType(type, "=");
	}

	private boolean checkType(String type, String target) {
		return target	.equalsIgnoreCase(type);
	}

	/*
	 * i.e. "min:<value>%"
	 */
	private boolean isMin(String type) {
		return checkType(type, "min");
	}

	/*
	 * i.e. "max:<value>%"
	 */
	private boolean isMax(String type) {
		return checkType(type, "max");
	}

	private String extractValue(String limit) {
		return limit.split(":")[1];
	}

	private static String extractType(String string) {
		return string.split(":")[0];
	}
}
