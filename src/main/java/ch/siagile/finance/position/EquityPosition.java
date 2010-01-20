package ch.siagile.finance.position;


import static java.text.MessageFormat.*;
import ch.siagile.finance.instrument.*;
import ch.siagile.finance.money.*;

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
		return price.times(quantity);
	}

	public boolean isEquity(String other) {
		return equity.equals(Equity.from(other));
	}

	@Override
	public String toString() {
		return format("Equity: {0} - {1} with price:{2} = {3}", equity, quantity, price, balance());
	}

}
