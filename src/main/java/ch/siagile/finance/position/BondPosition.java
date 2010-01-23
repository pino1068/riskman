package ch.siagile.finance.position;

import static java.text.MessageFormat.*;
import ch.siagile.finance.instrument.*;
import ch.siagile.finance.money.*;

public class BondPosition extends Position {
	private Bond bond;

	// for bonds the prices is in % and the quantity (nominal value) in
	// Money
	private final Money quantity;
	private final Percent price;

	private String owner;

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
		return bond.isLocated(areas);
	}
	
	@Override
	public String toString() {
		return format("bond {0}: {1} {2} = {3}",bond, quantity, price, balance());
	}

	public boolean isOwnedBy(String anOwner) {
		return owner.equals(anOwner);
	}

	public boolean isCalled(String aName) {
		return bond.isCalled(aName);
	}

	public BondPosition ownedBy(String anOwner) {
		this.owner = anOwner;
		return this;
	}
}
