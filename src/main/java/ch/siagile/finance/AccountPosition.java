package ch.siagile.finance;

public class AccountPosition extends BasePosition {

	public AccountPosition(String name, Money balance) {
		super(balance);
	}

	@Override
	public String toString() {
		return "account with balance: "+balance();
	}
}
