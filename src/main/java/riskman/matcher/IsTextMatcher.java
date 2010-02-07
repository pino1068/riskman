package riskman.matcher;

import org.hamcrest.*;

import riskman.position.*;

public class IsTextMatcher<T> extends BaseMatcher<T> {

	private final String[] freeText;

	public IsTextMatcher(String... definition) {
		this.freeText = definition;
	}

	public boolean matches(Object arg0) {
		if (Position.class.isInstance(arg0))
			return contains(arg0);
		return false;
	}

	private boolean contains(Object arg0) {
		for (String text : freeText)
			if (toPosition(arg0).isCalled(text))
				return true;
		return false;
	}

	private Position toPosition(Object arg0) {
		return (Position) arg0;
	}

	public void describeTo(Description arg0) {
		arg0.appendText("containing a text ");
		arg0.appendValue(freeText);
	}

}
