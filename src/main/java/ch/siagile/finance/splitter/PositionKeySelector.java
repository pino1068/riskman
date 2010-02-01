package ch.siagile.finance.splitter;

import ch.siagile.finance.position.*;

public class PositionKeySelector implements KeySelector {

	public Object selectKey(Position position) {
		return position;
	}

	public boolean isCalled(String string) {
		return "position".startsWith(string);
	}

}
