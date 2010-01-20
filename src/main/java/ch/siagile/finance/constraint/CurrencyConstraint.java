package ch.siagile.finance.constraint;

import org.hamcrest.*;

import ch.siagile.finance.check.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class CurrencyConstraint extends Constraint {

	private IsCurrenciesMatcher<Position> matcher;
	
	public CurrencyConstraint(String check, String... currencies) {
		super(Check.from(check));
		matcher = new IsCurrenciesMatcher<Position>(currencies);
	}

	@Override
	protected Matcher<Position> matcher() {
		return matcher;
	}

}
