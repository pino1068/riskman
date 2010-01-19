package ch.siagile.finance;

import static java.text.MessageFormat.*;

import org.hamcrest.*;

import ch.siagile.finance.instrument.*;

public class IsLocatedMatcher<T> extends BaseMatcher<T> {

	public static final <T> Matcher<T> located(String someAreas) {
		return new IsLocatedMatcher<T>(someAreas);
	}

	private final String areas;

	public IsLocatedMatcher(String areas) {
		this.areas = areas;
	}

	public boolean matches(Object obj) {
		if (isBondPosition(obj)) {
			return locationOf((BondPosition) obj);
		}
		if (isBond(obj)) {
			return locationOf((Bond) obj);
		}
		return false;
	}

	private boolean isBond(Object obj) {
		return Bond.class.isInstance(obj);
	}

	private boolean isBondPosition(Object obj) {
		return BondPosition.class.isInstance(obj);
	}

	private boolean locationOf(Bond bond) {
		return bond.isLocated(areas);
	}

	private boolean locationOf(BondPosition position) {
		return position.isLocated(areas);
	}

	public void describeTo(Description description) {
		description.appendText(format("matches an area = {0}", areas));
	}
}
