package riskman.position;

import riskman.money.*;

public abstract class BasePosition extends Position {
	
	private final Money balance;

	public BasePosition(Money balance) {
		this.balance = balance;
	}

	@Override
	public Money balance() {
		return balance;
	}

}
