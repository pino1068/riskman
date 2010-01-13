package ch.siagile.finance.instrument;

public class Equity {

	@Override
	public boolean equals(Object obj) {
		if (!Equity.class.isInstance(obj))
			return false;
		Equity equity = (Equity) obj;
		return equity.name.equals(name);
	}

	private final String name;

	public Equity(String name) {
		this.name = name;
	}

	public static Equity from(String name) {
		return new Equity(name);
	}

}
