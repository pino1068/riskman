package ch.siagile.finance.position;


import static java.text.MessageFormat.*;
import ch.siagile.finance.instrument.*;
import ch.siagile.finance.money.*;

public class EquityPosition extends BasePosition {
	private final Equity equity;
	private final int quantity;
	private final Money price;

	public EquityPosition(Equity equity, int quantity, Money price) {
		super(price.times(quantity));
		this.equity = equity;
		this.quantity = quantity;
		this.price = price;
	}

	public Money balance() {
		return price.times(quantity);
	}

	public boolean isCalled(String aName) {
		return equity.isCalled(aName);
	}

	@Override
	public String toString() {
		return format("{4} Equity: {0} - {1} with price:{2} = {3}", equity, quantity, price, balance(), super.toString());
	}

	public boolean isLocated(String[] areas) {
		return equity.isLocated(areas);
	}
}
