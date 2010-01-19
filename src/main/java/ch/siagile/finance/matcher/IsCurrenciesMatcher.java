package ch.siagile.finance.matcher;

import static org.hamcrest.Matchers.*;

import java.util.*;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public class IsCurrenciesMatcher<T> extends BaseMatcher<T> {

	private final String[] currencies;

	public Matcher<Position>[] toMatchers(String[] currencies) {
		@SuppressWarnings("unchecked")
		Matcher<Position>[] matchers = new Matcher[currencies.length];
		for (int i = 0; i < currencies.length; i++) {
			matchers[i] = new IsCurrencyMatcher<Position>(currencies[i]);
		}
		return matchers;
	}

	public IsCurrenciesMatcher(String... currencies) {
		check(currencies);
		this.currencies = currencies;
	}

	private void check(String... currencies) {
		for (String currency : currencies) {
			Currency.getInstance(currency);
		}
	}

	public boolean matches(Object position) {
		return anyOf(toMatchers(currencies)).matches(position);
	}

	public void describeTo(Description description) {
		description.appendText("exptected one of those currencies:");
		description.appendValue(currencies);
	}

}
