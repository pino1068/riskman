package ch.siagile.finance.location;

import java.util.*;

import ch.siagile.finance.repository.*;

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
}
