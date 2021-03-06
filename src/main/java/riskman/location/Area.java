package riskman.location;
import static java.text.MessageFormat.*;


public class Area {

	private final String name;

	private Area(String name) {
		this.name = name;
	}

	public static Area from(String name) {
		return new Area(name);
	}

	@Override
	public String toString() {
		return format("area: {0}", name);
	}

	@Override
	public boolean equals(Object obj) {
		if (!Area.class.isInstance(obj))
			return false;
		Area area = (Area) obj;
		if(area.name==null) return false;
		return area.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public String name() {
		return name;
	}

	public Area or(Area otherArea) {
		if(null == name || "".equals(name)) return otherArea;
		return this;
	}

}
