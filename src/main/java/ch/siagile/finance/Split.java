package ch.siagile.finance;

import java.util.*;

import ch.siagile.finance.position.*;

public class Split {

	private Map<Object, Positions> groups = new HashMap<Object, Positions>();

	public void add(Object object, Positions positions) {
		groups.put(object,positions);
	}

	public int size() {
		return groups.size();
	}

	public Iterable<Object> groups() {
		return groups.keySet();
	}

	public Positions positions(Object object) {
		if(groups.containsKey(object))
			return groups.get(object);
		add(object,new Positions());
		return positions(object);
	}

}
