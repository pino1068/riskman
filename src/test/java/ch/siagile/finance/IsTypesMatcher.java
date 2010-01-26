package ch.siagile.finance;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public class IsTypesMatcher<T> extends BaseMatcher<Position> {

	private final String[] types;

	public IsTypesMatcher(String... types) {
		this.types = types;
	}

	public boolean matches(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void describeTo(Description arg0) {
		// TODO Auto-generated method stub

	}

}
