package riskman.position;


import static java.text.MessageFormat.*;
import riskman.instrument.*;
import riskman.money.*;

public class EquityPosition extends BasePosition {
	private final Equity equity;
	private final double quantity;
	private final Money price;

	public EquityPosition(Equity equity, double quantity, Money price) {
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
		return equity.isLocatedIn(areas);
	}
}
