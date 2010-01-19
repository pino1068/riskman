package ch.siagile.finance.position;

import ch.siagile.finance.*;

public class BasePosition extends Position {
	
	private final Money balance;

	public BasePosition(Money balance) {
		this.balance = balance;
	}

	@Override
	public Money balance() {
		return balance;
	}

}
