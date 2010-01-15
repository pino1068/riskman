package ch.siagile.finance.instrument;

import ch.siagile.finance.*;
import ch.siagile.finance.instrument.rating.*;

public class Bond {

	private final String name;
	private final Rating rating;
	private final Area area;

	public static Bond from(String name, Rating rating) {
		return new Bond(name, rating, Area.from(null));
	}
	
	public static Bond from(String name) {
		return new Bond(name, Rating.NotRated(), Area.from(null));
	}

	public Bond(String name, Area area) {
		this(name, Rating.NotRated(), area);
	}

	public Bond(String name, Rating rating, Area area) {
		this.name = name;
		this.rating = rating;
		this.area = area;
	}

	public Rating rating() {
		return rating;
	}

	@Override
	public boolean equals(Object obj) {
		if(!Bond.class.isInstance(obj)) return false;
		Bond bond = (Bond)obj;
		return bond.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public static Bond from(String name, String area) {
		return new Bond(name,Area.from(area));
	}

	public boolean isLocated(String anArea) {
		return Area.from(anArea).equals(area);
	}

}
