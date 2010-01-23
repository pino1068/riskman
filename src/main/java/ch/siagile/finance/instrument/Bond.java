package ch.siagile.finance.instrument;

import static java.text.MessageFormat.*;
import ch.siagile.finance.location.*;

public class Bond {
	private final String name;
	private final Rating rating;

	public static Bond from(String name, Rating rating, Area anArea) {
		return new Bond(name, rating, anArea);
	}

	public static Bond from(String name, String area) {
		return new Bond(name, Rating.NotRated(), Area.from(area));
	}

	public Bond(String name, Rating rating, Area area) {
		this.name = name;
		this.rating = rating;
		Location.from(this).locateIn(area);
	}

	public Rating rating() {
		return rating;
	}

	@Override
	public boolean equals(Object obj) {
		if (isNotBond(obj))
			return false;
		return toBond(obj).name.equals(name);
	}

	private Bond toBond(Object obj) {
		return ((Bond) obj);
	}

	private boolean isNotBond(Object obj) {
		return !Bond.class.isInstance(obj);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public boolean isLocated(String... someAreas) {
		for (String anArea : someAreas) 
			if (Area.from(anArea).equals(area()))
				return true;
		return false;
	}

	private Area area() {
		return Location.from(this).area();
	}

	public boolean isCalled(String aName) {
		return name.equals(aName);
	}

	@Override
	public String toString() {
		return format("{0} {1}", name, rating);
	}
}