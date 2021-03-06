package riskman.position;

import static riskman.Some.*;

import java.util.*;

import riskman.constraint.*;
import riskman.money.*;

public class Positions implements Iterable<Position> {
	
	private List<Position> positions = new ArrayList<Position>();

	public Positions(Position... positions) {
		for (Position position : positions) 
			add(position);
	}

	public void add(Position aPosition) {
		positions.add(aPosition);
	}

	public Money value() {
		Money totalAmount = Money.money(0, "CHF");
		for (Position position : positions) 
			totalAmount = totalAmount.plus(position.balance());
		return totalAmount;
	}

	public Positions select(Filter filter) {
		Positions result = new Positions();
		for (Position position : positions) 
			if (filter.matches(position))
				result.add(position);
		return result;
	}

	@Override
	public String toString() {
		return some(positions).toString();
	}

	public Ratio divideBy(Positions other) {
		return value().divideBy(other.value());
	}

	public Iterator<Position> iterator() {
		return positions.iterator();
	}

	public Ratio ratioOn(Filter filter) {
		Positions selectedPositions = select(filter);
		return selectedPositions.divideBy(this);
	}

	public Percent percentOf(Positions other) {
		return divideBy(other).percent();
	}

	public int size() {
		return positions.size();
	}

	public boolean isEmpty() {
		return positions.isEmpty();
	}

	public void addAll(Positions some) {
		positions.addAll(some.positions);
	}

	public Positions sortByName() {
		List<Position> list = new ArrayList<Position>(positions);
		Collections.sort(list, new ByName());
		Positions result = new Positions();
		for (Position position : list) 
			result.add(position);
		return result;
	}
	
	private class ByName implements Comparator<Position>{

		public int compare(Position o1, Position o2) {
			return o1.toString().compareTo(o2.toString());
		}

		
	}
	
}