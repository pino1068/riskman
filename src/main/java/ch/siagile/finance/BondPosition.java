package ch.siagile.finance;

public class BondPosition extends BasePosition {

	private final String name;

	public BondPosition(String name, Money balance) {
		super(balance);
		this.name = name;
	}

}
