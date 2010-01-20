package ch.siagile.finance.position;

import static java.text.MessageFormat.*;
import ch.siagile.finance.money.*;

public class AccountPosition extends BasePosition {

	private final String name;

	private String owner;

	public AccountPosition(String name, Money balance) {
		super(balance);
		this.name = name;
	}

	public boolean isOwnedBy(String string) {
		return string.equals(owner);
	}

	@Override
	public String toString() {
		return format("{0} {1}", name, balance());
	}

	public AccountPosition ownedBy(String anOwner) {
		this.owner = anOwner;
		return this;
	}

	public boolean isCalled(String aName) {
		return name.equals(aName);
	}

	@Override
	public boolean equals(Object obj) {
		if (AccountPosition.class.isInstance(obj)) {
			AccountPosition other = (AccountPosition) obj;
			if (!name.equals(other.name))
				return false;
			if (!balance().equals(other.balance()))
				return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 13 * name.hashCode() + 17 * balance().hashCode();
	}
}
