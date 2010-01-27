package ch.siagile.finance.position;

import ch.siagile.finance.money.*;

public class BasePosition extends Position {
	
	private final Money balance;
	private String owner;

	public BasePosition(Money balance) {
		this.balance = balance;
	}

	@Override
	public Money balance() {
		return balance;
	}

	public boolean isOwnedBy(String string) {
		return string.equals(owner);
	}

	public Position ownedBy(String anOwner) {
		this.owner = anOwner;
		return this;
	}

}
