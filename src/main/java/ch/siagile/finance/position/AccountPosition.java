package ch.siagile.finance.position;


import static java.text.MessageFormat.*;
import ch.siagile.finance.money.*;


public class AccountPosition extends BasePosition {

	private final String name;

	public AccountPosition(String name, Money balance) {
		super(balance);
		this.name = name;
	}

	@Override
	public String toString() {
		return format("account \"{0}\"		with balance: {1} ",name, balance());
	}
}
