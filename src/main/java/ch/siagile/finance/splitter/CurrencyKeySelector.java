package ch.siagile.finance.splitter;

import ch.siagile.finance.position.*;

public class CurrencyKeySelector implements KeySelector {

	public Object selectKey(Position position) {
		return position.currency();
	}

	public boolean isCalled(String string) {
		return "currency".startsWith(string);
	}

}
