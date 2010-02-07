package riskman.splitter;

import riskman.position.*;

public class PositionKeySelector implements KeySelector {

	public Object selectKey(Position position) {
		return position;
	}

	public boolean isCalled(String string) {
		return "position".startsWith(string);
	}

}
