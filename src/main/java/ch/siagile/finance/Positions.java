package ch.siagile.finance;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Positions {

	private List<Position> positions = new ArrayList<Position>();

	public Positions(Position... positions) {
		for (Position position : positions) {
			add(position);
		}
	}

	@Deprecated
	public void add(Position position) {
		this.positions.add(position);
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
		return this.positions.toString();
	}

	public Ratio divideBy(Positions other) {
		return value().divideBy(other.value());
	}

}
