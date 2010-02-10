package riskman.location;

import java.util.*;

import riskman.repository.*;

public class Areas implements Iterable<Area> {

	private Set<Area> areas = new HashSet<Area>();

	public static Areas all(){
		return new AreaRepository().all();
	}

	public void add(String name) {
		add(Area.from(name));
	}

	public void add(Area area) {
		areas.add(area);
	}

	public int size() {
		return areas.size();
	}

	public Iterator<Area> iterator() {
		return areas.iterator();
	}

	public boolean contains(String anAreaName) {
		return areas.contains(Area.from(anAreaName));
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Area area : areas) {
			result.append(area.name()).append("\n");
		}
		return result.toString();
	}
}
