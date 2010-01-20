package ch.siagile.finance.position;

import java.util.*;

import org.hamcrest.*;

import ch.siagile.finance.money.*;

public class Positions implements Iterable<Position> {
	private List<Position> positions = new ArrayList<Position>();

	public Positions(Position... positions) {
		for (Position position : positions) {
			add(position);
		}
	}

	public void add(Position aPosition) {
		positions.add(aPosition);
	}

	public Money value() {
		Money totalAmount = Money.from(0, "CHF");
		for (Position position : positions) {
			totalAmount = totalAmount.plus(position.balance());
		}
		return totalAmount;
	}

	public Positions select(Matcher<Position> matcher) {
		Positions result = new Positions();
		for (Position position : positions) {
			if (matcher.matches(position))
				result.add(position);
		}
		return result;
	}

	@Override
	public String toString() {
		String result = "";
		for (Position position : positions) {
			result += position.toString() + "\n";
		}
		return result;
	}

	public Ratio divideBy(Positions other) {
		return value().divideBy(other.value());
	}

	@Override
	public Iterator<Position> iterator() {
		return positions.iterator();
	}
}