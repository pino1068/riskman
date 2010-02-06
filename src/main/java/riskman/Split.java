package riskman;

import java.util.*;

import riskman.check.*;
import riskman.money.*;
import riskman.position.*;
import riskman.splitter.*;

public class Split {

	private Map<Object, Positions> groups = new HashMap<Object, Positions>();
	private SplitCheckListener listener;

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

	public void chechLimitOn(Check checker, Positions positions) {
		for (Object group : groups()) {
			Ratio ratio = positions(group).divideBy(positions);
			if (checker.check(ratio))
				listener.success(group, ratio);
			else
				listener.failure(group, ratio);
		}
	}

	public void listener(SplitCheckListener listener) {
		this.listener = listener;
		
	}

}
