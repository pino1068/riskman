package ch.siagile.finance;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public class IsBondMatcher<T> extends BaseMatcher<T> {

	public boolean matches(Object obj) {
		return BondPosition.class.isInstance(obj);
	}

	public void describeTo(Description description) {
		description.appendText("is a bond position");
	}


}
