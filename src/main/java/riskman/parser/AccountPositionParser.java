package riskman.parser;

import riskman.money.*;
import riskman.position.*;

public class AccountPositionParser extends BaseInstrumentParser {

	private static final int BALANCE = 3;

	public Position parsePosition(String string) {
		return new AccountPosition(name(), balance()).ownedBy(owner());
	}

	protected Money balance() {
		return fMoney(BALANCE, CURRENCY);
	}

	@Override
	protected String discriminator() {
		return "L";
	}
}
