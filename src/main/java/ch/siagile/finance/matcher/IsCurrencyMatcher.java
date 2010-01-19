package ch.siagile.finance.matcher;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public class IsCurrencyMatcher<T> extends BaseMatcher<T> {

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
