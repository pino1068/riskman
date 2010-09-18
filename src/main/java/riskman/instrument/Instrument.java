package riskman.instrument;

import static java.text.MessageFormat.*;
import riskman.location.*;

public abstract class Instrument {

	private final String name;
	private final String id;

	public Instrument(String id, String name) {
		this.id = id;
		this.name = name;
	}

	protected abstract boolean isNotMyType(Object obj);

	@Override
	public String toString() {
		return format("{0} / {1}",id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (isNotMyType(obj))
			return false;
		return toInstrument(obj).isCalled(name);
	}

	@Override
	public int hashCode() {
		return id.hashCode() * 13 + getClass().hashCode() * 17;
	}

	private Instrument toInstrument(Object obj) {
		return (Instrument) obj;
	}

	public boolean isCalled(String aName) {
		return name.equals(aName);
	}

	public boolean isLocatedIn(String... areas) {
		return Location.from(this).isLocatedIn(areas);
	}

	public String name() {
		return name;
	}

	public String id() {
		return id;
	}
}
