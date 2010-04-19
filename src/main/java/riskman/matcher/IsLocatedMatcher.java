package riskman.matcher;

import static java.text.MessageFormat.*;

import org.hamcrest.*;

import riskman.instrument.*;
import riskman.position.*;

public class IsLocatedMatcher<T> extends BaseMatcher<T> {

	public static final <T> Matcher<T> located(String... someAreas) {
		return new IsLocatedMatcher<T>(someAreas);
	}

	private final String[] areas;

	public IsLocatedMatcher(String... areas) {
		this.areas = areas;
	}

	public boolean matches(Object obj) {
		if(isEquityPosition(obj))
			return isLocated((EquityPosition)obj);

		if (isBondPosition(obj)) 
			return isLocated((BondPosition) obj);
		
		if (isBond(obj)) 
			return isLocated((Bond) obj);
		
		return false;
	}

	private boolean isLocated(EquityPosition obj) {
		return obj.isLocated(areas);
	}

	private boolean isEquityPosition(Object obj) {
		return EquityPosition.class.isInstance(obj);
	}

	private boolean isBond(Object obj) {
		return Bond.class.isInstance(obj);
	}

	private boolean isBondPosition(Object obj) {
		return BondPosition.class.isInstance(obj);
	}

	private boolean isLocated(Bond bond) {
		return bond.isLocatedIn(areas);
	}

	private boolean isLocated(BondPosition position) {
		return position.isLocated(areas);
	}

	public void describeTo(Description description) {
		description.appendText(format("matches an area = {0}", (Object[]) areas));
	}
}
