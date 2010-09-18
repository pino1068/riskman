package riskman.parser;

import static java.text.MessageFormat.*;
import riskman.instrument.*;
import riskman.money.*;
import riskman.position.*;

public class BondPositionParser extends PositionParser {

	private static final int ID = 0;
	private static final int INSTRUMENTID = 17;
	private static final int PRICE = 7;
	private static final int QUANTITY = 3;
	private static final int CURRENCY = 2;
	private static final int NAME = 5;
	private static final int OWNER = 0;

	public Position parsePosition(String string) {
		return new BondPosition(bond(), quantity(), price()).ownedBy(owner());
	}

	private String owner() {
		return f(OWNER);
	}

	private Percent price() {
		return Percent.from(format("{0}%", f(PRICE, "0")));
	}

	private Money quantity() {
		return fMoney(QUANTITY, CURRENCY);
	}

	private Bond bond() {
		return Bond.from(f(ID),f(NAME), "UE");
	}

	public boolean recognizePosition(String string) {
		return f(INSTRUMENTID).equals("2");
	}
}
