package ch.siagile.finance;

public class Area {

	private final String name;

	public Area(String name) {
		this.name = name;
	}

	public static Area from(String name) {
		return new Area(name);
	}

	@Override
	public String toString() {
		return "Area: " + name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!Area.class.isInstance(obj))
			return false;
		Area area = (Area) obj;
		return area.name.equals(name);
	}

}
