package ch.siagile.finance;

import java.util.*;

public class Areas implements Iterable<Area> {

	private Set<Area> areas = new HashSet<Area>();

	public static Areas defaultAreas = new Areas() {
		{
			add("CH");
			add("GBP");
			add("NORD AMERICA");
			add("PACIFICO");
			add("UE");
			add("USA");
		}
	};

	public void add(String name) {
		areas.add(Area.from(name));
	}

	public int size() {
		return areas.size();
	}

	@Override
	public Iterator<Area> iterator() {
		return areas.iterator();
	}
}
