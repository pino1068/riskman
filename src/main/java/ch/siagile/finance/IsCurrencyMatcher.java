package ch.siagile.finance;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class IsCurrencyMatcher extends BaseMatcher<Position> {

	private String currency;

	public IsCurrencyMatcher(String currency) {
		this.currency = currency;
	}

	public boolean matches(Object obj) {
		if(!Position.class.isInstance(obj)) return false;
		Position position = (Position)obj;
		return position.balance().compatible(currency);
	}

	public void describeTo(Description description) {
		description.appendText("exptected currency: ");
		description.appendValue(currency);
	}

}
