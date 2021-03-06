package riskman.matcher;

import org.hamcrest.*;

import riskman.position.*;

public class IsAccountMatcher<T> extends BaseMatcher<T> {

	public void describeTo(Description description) {
		description.appendText("matches when the position is an Account position");
	}

	public boolean matches(Object item) {
		if(AccountPosition.class.isInstance(item)) return true;
		return false;
	}
}
