package riskman.location;

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

	public void locateIn(Area area) {
		this.area = area;
	}

	public boolean isLocatedIn(Area anOther) {
		if (area == null)
			return false;
		return area.equals(anOther);
	}

	public boolean isLocatedIn(String someAreaNames) {
		return isLocatedIn(split(someAreaNames));
	}

	private String[] split(String someAreaNames) {
		return someAreaNames.split(",");
	}

	public boolean isLocatedIn(String[] someAreas) {
		for (String anArea : someAreas)
			if (Area.from(anArea).equals(area()))
				return true;
		return false;
	}
}
