package ch.siagile.finance;

public abstract class Check {

	public abstract boolean check(Ratio value);

	public static Check from(String limit, double value) {
		if ("max".equalsIgnoreCase(limit.trim()))
			return new MAXCheck(value);
		return new MINCheck(value);
	}

	public static Check from(String limit) {
		return new CheckParser(limit).parse();
	}

}
