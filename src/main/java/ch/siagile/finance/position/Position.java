package ch.siagile.finance.position;

import ch.siagile.finance.money.*;

public abstract class Position {

	public abstract Money balance();

	public Money sumBalance(Position position) {
		return balance().plus(position.balance());
	}

	public abstract boolean isCalled(String freeText);

	private String owner;
	public boolean isOwnedBy(String anOwner) {
		return anOwner.equals(owner);
	}

	public Position ownedBy(String anOwner) {
		this.owner = anOwner;
		return this;
	}
}
