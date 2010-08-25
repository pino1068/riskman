package riskman.position;

import static java.text.MessageFormat.*;
import riskman.instrument.*;
import riskman.instrument.rating.*;
import riskman.money.*;

public class BondPosition extends Position {
	private Bond bond;

	// for bonds the prices is in % and the quantity (nominal value) in
	// Money
	private final Money quantity;
	private final Percent price;

	public BondPosition(Bond bond, Money quantity, Percent price) {
		this.bond = bond;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public Money balance() {
		return this.quantity.times(price);
	}

	public Rating rating() {
		return bond.rating();
	}

	public boolean isLocated(String... areas) {
		return bond.isLocatedIn(areas);
	}
	
	@Override
	public String toString() {
		return format("{4} Bond {0}: {1} {2} = {3}",bond, quantity, price, balance(),super.toString());
	}

	public boolean isCalled(String aName) {
		return bond.isCalled(aName);
	}

	public Bond bond() {
		return bond;
	}
}
