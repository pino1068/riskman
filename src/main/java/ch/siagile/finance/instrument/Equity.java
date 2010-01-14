package ch.siagile.finance.instrument;

import static java.text.MessageFormat.*;



public class Equity {

	private final String name;

	public Equity(String name) {
		this.name = name;
	}

	public static Equity from(String name) {
		return new Equity(name);
	}
	
	@Override
	public String toString() {
		return format("{0}", name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!Equity.class.isInstance(obj)) return false;
		Equity equity = (Equity)obj;
		return equity.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
