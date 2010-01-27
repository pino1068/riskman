package ch.siagile.finance.instrument;

import static java.text.MessageFormat.*;
import ch.siagile.finance.location.*;

public abstract class Instrument {

	private final String name;

	public Instrument(String name) {
		this.name = name;
	}
	
	protected abstract boolean isNotMyType(Object obj);
	
	@Override
	public String toString() {
		return format("{0}", name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(isNotMyType(obj)) return false;
		return toInstrument(obj).isCalled(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode()*13 + getClass().hashCode()*17;
	}

	private Instrument toInstrument(Object obj) {
		return (Instrument)obj;
	}

	public boolean isCalled(String aName) {
		return name.equals(aName);
	}

	public boolean isLocated(String... areas) {
		return Location.from(this).isLocatedIn(areas);
	}
	
	public String name(){
		return name;
	}
}
