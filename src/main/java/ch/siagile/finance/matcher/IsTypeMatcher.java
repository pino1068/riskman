package ch.siagile.finance.matcher;

import java.util.*;

import org.hamcrest.*;
import org.hamcrest.core.*;

import ch.siagile.finance.position.*;

public class IsTypeMatcher<T> extends BaseMatcher<T> {

	private Matcher<Position> matcher;
	private final String[] types;
	private Map<String, Matcher<? extends T>> matcherTypes = new HashMap<String, Matcher<? extends T>>(){
		private static final long serialVersionUID = 1L;
		{
			put("bond", new IsBondMatcher<T>());
			put("equity", new IsEquityMatcher<T>());
			put("account", new IsAccountMatcher<T>());
		}
	};

	public IsTypeMatcher(String... types) {
		this.types = types;
		matcher = matcherFrom(types);
	}

	private Matcher<Position> matcherFrom(String... types) {
		Set<Matcher<? extends T>> matchers = new HashSet<Matcher<? extends T>>();
		for (String type : types) 
			if(matcherTypes.containsKey(type))
				matchers.add(matcherTypes.get(type));
		return anyOf(matchers);
	}

	@SuppressWarnings("unchecked")
	private Matcher<Position> anyOf(Iterable<Matcher<? extends T>> matchers) {
		return (Matcher<Position>) new AnyOf<T>(matchers);
	}

	public boolean matches(Object position) {
		return matcher.matches(position);
	}

	public void describeTo(Description describe) {
		describe.appendText("is one of ");
		describe.appendValue(types);
	}

}
