package ch.siagile.finance.position;

import static ch.siagile.finance.Some.*;

import java.util.*;

import ch.siagile.finance.constraint.*;
import ch.siagile.finance.money.*;

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
		Money totalAmount = Money.from(0, "CHF");
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
}