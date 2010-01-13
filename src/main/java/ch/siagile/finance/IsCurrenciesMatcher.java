package ch.siagile.finance;

import static org.hamcrest.Matchers.*;

import org.hamcrest.*;

public class IsCurrenciesMatcher extends BaseMatcher<Position> {

	private Matcher<Position> delegate;
	private final String[] currencies;

	public IsCurrenciesMatcher(String... currencies) {
		this.currencies = currencies;
		this.delegate = anyOf(toMatchers(currencies));
	}

	private Matcher<Position>[] toMatchers(String... currencies) {
		@SuppressWarnings("unchecked")
		Matcher<Position>[] matchers = new Matcher[currencies.length];
		for (int i = 0; i < currencies.length; i++) {
			matchers[i] = new IsCurrencyMatcher(currencies[i]);
		}
		return matchers;
	}

	public boolean matches(Object position) {
		return this.delegate.matches(position);
	}

	public void describeTo(Description description) {
		description.appendText("exptected one of those currencies:");
		description.appendValue(currencies);
	}

}
