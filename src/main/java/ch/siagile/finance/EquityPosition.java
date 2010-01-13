package ch.siagile.finance;

import ch.siagile.finance.instrument.*;

public class EquityPosition extends Position {
	private final Equity equity;
	private final int quantity;
	private final Money price;

	public EquityPosition(Equity equity, int quantity, Money price) {
		this.equity = equity;
		this.quantity = quantity;
		this.price = price;
	}

	public Money balance() {
		return this.price.times(this.quantity);
	}

	public boolean isEquity(String equity) {
		return this.equity.equals(Equity.from(equity));
	}

	@Override
	public String toString() {
		return "Equity:" + equity + " - " + quantity + " with price:" + price + " = " + balance();
	}

}
