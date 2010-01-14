package ch.siagile.finance;
import static java.text.MessageFormat.*;

import org.hamcrest.*;

import ch.siagile.finance.instrument.*;

@SuppressWarnings("unchecked")
public class AreaMatcher extends BaseMatcher {

	private final String area;

	public AreaMatcher(String area) {
		this.area = area;
	}

	public boolean matches(Object obj) {
		if(!Bond.class.isInstance(obj)) return false;
		Bond bond = (Bond) obj;
		return bond.isLocated(area);
	}

	public void describeTo(Description description) {
		description.appendText(format("matches an area = {0}",area));
	}
}
