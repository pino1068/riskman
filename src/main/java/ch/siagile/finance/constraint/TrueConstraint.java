package ch.siagile.finance.constraint;

import org.hamcrest.*;

import ch.siagile.finance.check.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class TrueConstraint extends Constraint {

	public TrueConstraint() {
		super(Check.from("true"));
	}
	
	@Override
	protected Matcher<Position> matcher() {
		return new TrueMatcher<Position>();
	}

}
