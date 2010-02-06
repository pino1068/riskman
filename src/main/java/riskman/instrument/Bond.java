package riskman.instrument;

import static java.text.MessageFormat.*;
import riskman.instrument.rating.*;
import riskman.location.*;

public class Bond extends Instrument {
	private final Rating rating;

	public static Bond from(String name, Rating rating, Area anArea) {
		return new Bond(name, rating, anArea);
	}

	public static Bond from(String name, Rating rating, String anArea) {
		return new Bond(name, rating, Area.from(anArea));
	}

	public static Bond from(String name, String area) {
		return new Bond(name, Rating.NotRated(), Area.from(area));
	}

	public Bond(String name, Rating rating, Area area) {
		super(name);
		this.rating = rating;
		Location.from(this).locateIn(area);
	}

	public Rating rating() {
		return rating;
	}
	
	@Override 
	protected boolean isNotMyType(Object obj) {
		return !Bond.class.isInstance(obj);
	}

	@Override
	public String toString() {
		return format("{0} {1}", name(), rating);
	}
}