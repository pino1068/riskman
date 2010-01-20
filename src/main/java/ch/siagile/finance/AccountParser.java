package ch.siagile.finance;

import ch.siagile.finance.money.*;
import ch.siagile.finance.position.*;

public class AccountParser extends Parser {

	private static final int BALANCE = 3;
	private static final int INSTRUMENT = 1;
	private static final int CURRENCY = 2;
	private static final int NAME = 5;
	private static final int OWNER = 0;

	public AccountPosition parse(String string) {
		fields(string);
		return new AccountPosition(name(), balance()).ownedBy(owner());
	}
	
	private Money balance() {
		return fMoney(BALANCE, CURRENCY);
	}

	private String name() {
		return f(NAME);
	}

	private String owner() {
		return f(OWNER);
	}

	public boolean recognize(String string) {
		fields(string);
		return f(INSTRUMENT).equals("L");
	}
}
