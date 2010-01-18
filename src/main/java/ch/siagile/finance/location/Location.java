package ch.siagile.finance.location;

import ch.siagile.finance.*;


public class Location {

	private final static Locations locations = new Locations();

	public static Location from(Object obj) {
		return locations.from(obj);
	}

	private Area area;

	public Area area() {
		return area;
	}

	public void locateIn(String area) {
		this.area = Area.from(area);
	}

	public void locate(Area area) {
		this.area = area;
	}

	public boolean isLocatedIn(Area anOther) {
		if(area==null) return false;
		return area.equals(anOther);
	}

	public boolean isLocatedIn(String someAreaNames) {
		for (String anArea : someAreaNames.split(",")) {
			if (isLocatedIn(Area.from(anArea)))
				return true;
		}
		return false;
	}
}
