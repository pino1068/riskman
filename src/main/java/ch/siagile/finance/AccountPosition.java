package ch.siagile.finance;


import static java.text.MessageFormat.*;


public class AccountPosition extends BasePosition {

	public AccountPosition(String name, Money balance) {
		super(balance);
	}

	@Override
	public String toString() {
		return format("account with balance: {1}", balance());
	}
}
