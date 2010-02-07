package riskman.fixtures;

import riskman.instrument.*;
import riskman.location.*;
import riskman.money.*;
import riskman.position.*;

public class Fixtures {
	public static final int IBM_PRICE = 100;
	public static final int UBS_PRICE = 10;

	public static Position IBM(Money price) {
		return equity("IBM", 1, price);
	}

	public static Position UBS(Money price) {
		return equity("UBS", 1, price);
	}

	public static Position equity(String equity, int quantity, Money price) {
		Equity myEquity = Equity.from(equity);
		Location.from(myEquity).locateIn("UE");
		return new EquityPosition(myEquity, quantity, price).ownedBy("equityOwner");
	}

	public static Position bond(Bond bond, Money quantity, String price) {
		return new BondPosition(bond, quantity, Percent.from(price)).ownedBy("bondOwner");
	}

	public static Position account(String name, Money balance) {
		return new AccountPosition(name, balance).ownedBy("accountOwner");
	}

}
