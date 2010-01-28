package ch.siagile.finance.matcher;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public class IsOwnerMatcher<T> extends BaseMatcher<T> {

	private final String[] owners;

	public IsOwnerMatcher(String[] owners) {
		this.owners = owners;
	}

	public boolean matches(Object arg0) {
		if (Position.class.isInstance(arg0))
			return isOwnedByOneOf(arg0);
		return false;
	}

	private boolean isOwnedByOneOf(Object arg0) {
		for (String owner : owners) 
			if(toPosition(arg0).isOwnedBy(owner))
				return true;
		return false;
	}

	private Position toPosition(Object arg0) {
		return (Position) arg0;
	}

	public void describeTo(Description arg0) {
		arg0.appendText("is owned by ");
		arg0.appendValue(owners);
	}

}
