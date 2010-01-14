package ch.siagile.finance;

import static org.hamcrest.Matchers.*;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsCurrenciesMatcher extends BaseMatcher<Position> {

	private final String[] currencies;

	public Matcher<Position>[] toMatchers(String[] currencies) {
		@SuppressWarnings("unchecked")
		Matcher<Position>[] matchers = new Matcher[currencies.length];
		for (int i = 0; i < currencies.length; i++) {
			matchers[i] = new IsCurrencyMatcher(currencies[i]);
		}
		return matchers;
	}

	public IsCurrenciesMatcher(String... currencies) {
		this.currencies = currencies;
	}

	public boolean matches(Object position) {
		return anyOf(toMatchers(currencies)).matches(position);
	}

	public void describeTo(Description description) {
		description.appendText("exptected one of those currencies:");
		description.appendValue(currencies);
	}

}
