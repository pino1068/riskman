package riskman.splitter;

import riskman.position.*;

public class CurrencyKeySelector implements KeySelector {

	public Object selectKey(Position position) {
		return position.currency();
	}

	public boolean isCalled(String string) {
		return "currency".startsWith(string);
	}

}
