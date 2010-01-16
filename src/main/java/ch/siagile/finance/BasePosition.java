package ch.siagile.finance;

import static java.text.MessageFormat.*;

public class BasePosition extends Position {
	
	private final Money balance;

	public BasePosition(Money balance) {
		this.balance = balance;
	}

	@Override
	public Money balance() {
		return balance;
	}

	@Override
	public String toString() {
		return format("with {1}", balance);
	}

}
