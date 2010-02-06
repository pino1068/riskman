package riskman.splitter;

import riskman.position.*;

public class TypeKeySelector implements KeySelector {

	public Object selectKey(Position position) {
		return position.getClass();
	}

	public boolean isCalled(String string) {
		return "type".startsWith(string);
	}

}
