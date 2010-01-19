package ch.siagile.finance;

public abstract class Check {

	public abstract boolean check(Ratio value);

	public static Check from(String limit) {
		return new CheckBuilder(limit).build();
	}

}
