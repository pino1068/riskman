package ch.siagile.finance;

import static java.text.MessageFormat.*;
import ch.siagile.finance.instrument.*;
import ch.siagile.finance.instrument.rating.*;

public class BondPosition extends Position {
	private Bond bond;

	// for bonds the prices is espress in % and the quantity (nominal value) in
	// Money
	private final Money quantity;
	private final double price;

	public BondPosition(Bond bond, Money quantity, String price) {
		this.bond = bond;
		this.quantity = quantity;
		String percent = price.split("%")[0];
		this.price = Double.valueOf(percent);
	}

	@Override
	public Money balance() {
		return this.quantity.times(price).divideBy(100);
	}

	public Rating rating() {
		return bond.rating();
	}

	public boolean isLocated(String anArea) {
		return bond.isLocatedIn(anArea);
	}

	@Override
	public String toString() {
		return format(
				"bond position ({0}) with quantity:{1} and price:{2} whos balance is: {3}",
				bond, quantity, price, balance());
	}

}
