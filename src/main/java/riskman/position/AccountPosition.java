package riskman.position;

import static java.text.MessageFormat.*;
import riskman.money.*;

public class AccountPosition extends BasePosition {

	private final String name;

	public AccountPosition(String name, Money balance) {
		super(balance);
		this.name = name;
	}

	@Override
	public String toString() {
		return format("{2} Account: {0} {1}", name, balance(), super.toString());
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
