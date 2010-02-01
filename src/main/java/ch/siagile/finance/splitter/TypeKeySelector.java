package ch.siagile.finance.splitter;

import ch.siagile.finance.position.*;

public class TypeKeySelector implements KeySelector {

	public Object selectKey(Position position) {
		return position.getClass();
	}

	public boolean isCalled(String string) {
		return "type".startsWith(string);
	}

}
