package riskman.location;

import java.util.*;

public class Locations {

	private static final Map<Object,Location> locations = new HashMap<Object, Location>();

	public Location from(Object obj) {
		if(locations.containsKey(obj))
			return locations.get(obj);
		locations.put(obj, new Location());
		return from(obj);
	}

}
