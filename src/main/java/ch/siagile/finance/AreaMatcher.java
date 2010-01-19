package ch.siagile.finance;


import static java.text.MessageFormat.*;

import org.hamcrest.*;

import ch.siagile.finance.instrument.*;

public class AreaMatcher<T> extends BaseMatcher<T> {

	private final String area;

	public AreaMatcher(String area) {
		this.area = area;
	}

	public boolean matches(Object obj) {
		if (BondPosition.class.isInstance(obj)) {
			BondPosition position = (BondPosition) obj;
			return position.isLocated(area);
		}
		if (!Bond.class.isInstance(obj))
			return false;
		Bond bond = (Bond) obj;
		return bond.isLocated(area);
	}

	public void describeTo(Description description) {
		description.appendText(format("matches an area = {0}", area));
	}
}
