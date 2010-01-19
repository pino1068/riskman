package ch.siagile.finance;

public abstract class Position {

	public abstract Money balance();

	public Money sumBalance(Position position) {
		return balance().plus(position.balance());
	}

}
