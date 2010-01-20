package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class PositionMenu extends BaseMenu {

	@Override
	public void perform(Positions positions, String line) {
		println("Actual positions are:");
		println(positions.toString());
	}

	@Override
	public boolean isCalled(String line) {
		return line.startsWith("p");
	}

	@Override
	public String describe() {
		return "		'p' or 'positions'			- print all positions";
	}
}
