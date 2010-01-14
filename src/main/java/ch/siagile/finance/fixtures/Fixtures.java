package ch.siagile.finance.fixtures;

import ch.siagile.finance.AccountPosition;
import ch.siagile.finance.BondPosition;
import ch.siagile.finance.EquityPosition;
import ch.siagile.finance.Money;
import ch.siagile.finance.Position;
import ch.siagile.finance.instrument.Bond;
import ch.siagile.finance.instrument.Equity;

public class Fixtures {
	public static final int IBM_PRICE = 100;
	public static final int UBS_PRICE = 10;

	public static Position UBS(int quantity) {
		return equity("UBS", quantity, CHF(UBS_PRICE));
	}
	public static Position IBM(int quantity) {
		return equity("IBM", quantity, CHF(IBM_PRICE));
	}
	public static EquityPosition equity(String equity, int quantity, Money price) {
		return new EquityPosition(Equity.from(equity), quantity, price);
	}
	public static BondPosition bond(Bond bond, Money quantity, String price) {
		return new BondPosition(bond, quantity, price);
	}
	public static AccountPosition account(String name, Money balance) {
		return new AccountPosition(name, balance);
	}
	public static Money CHF(double price) {
		return Money.from(price, "CHF");
	}
	public static Money USD(double amount) {
		return Money.from(amount, "USD");
	}

}
