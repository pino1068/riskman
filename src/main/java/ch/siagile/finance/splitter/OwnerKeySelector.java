package ch.siagile.finance.splitter;

import ch.siagile.finance.position.*;

public class OwnerKeySelector implements KeySelector {

	public Object selectKey(Position position) {
		return position.owner();
	}

	public boolean isCalled(String string) {
		return "owner".startsWith(string);
	}

}
