package ch.siagile.finance;

import java.util.*;

public class Areas {

	private Set<Area> areas = new HashSet<Area>();

	public static Areas list() {
		return Factory.repository().allAreas();
	}

	public void add(String name) {
		this.areas.add(Area.from(name));
	}

	public int size() {
		return this.areas.size();
	}

}
