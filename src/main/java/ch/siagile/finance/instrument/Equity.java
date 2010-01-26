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
		if(isNotEquity(obj)) return false;
		return toEquity(obj).isCalled(name);
	}

	private boolean isNotEquity(Object obj) {
		return !Equity.class.isInstance(obj);
	}

	private Equity toEquity(Object obj) {
		return (Equity)obj;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public boolean isCalled(String aName) {
		return name.equals(aName);
	}
}
