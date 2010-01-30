package ch.siagile.finance.constraint;


import static java.text.MessageFormat.*;
import ch.siagile.finance.check.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class Constraint {

	private final Check check;
	private final Filter filter;

	public Constraint(Check check) {
		filter = null;
		this.check = check;
	}

	public Constraint(Filter filter, Check check) {
		this.filter = filter;
		this.check = check;
	}

	public boolean checkLimitOn(Positions positions) {
		return new CheckLimitOn(positions, filter(), check).isValid();
	}

	protected Filter filter(){
		return filter;
	}

	@Override
	public String toString() {
		return format("{0} with limit: {1}", filter(), check);
	}

	public Positions filter(Positions positions) {
		return positions.select(filter());
	}

	public static Constraint from(String filter, String check) {
		return new Constraint(FilterBuilder.from(filter), Check.from(check)) ;
	}

	public static Constraint from(Filter filter, Check check) {
		return new Constraint(filter, check);
	}
}
