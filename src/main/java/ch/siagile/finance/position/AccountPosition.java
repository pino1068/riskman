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
		return format("account \"{0}\"		with balance: {1} ",name, balance());
	}

	public AccountPosition ownedBy(String anOwner) {
		this.owner = anOwner;
		return this;
	}

	public boolean isCalled(String aName) {
		return name.equals(aName);
	}
}
