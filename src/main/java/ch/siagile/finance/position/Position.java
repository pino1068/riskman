package ch.siagile.finance.position;

import ch.siagile.finance.base.*;

public abstract class Position {

	public abstract Money balance();

	public Money sumBalance(Position position) {
		return balance().plus(position.balance());
	}

}
