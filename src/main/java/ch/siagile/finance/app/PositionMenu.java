package ch.siagile.finance.app;

import static java.text.MessageFormat.*;
import ch.siagile.finance.position.*;

public class PositionMenu extends BaseMenu {

	@Override
	public void perform(AppContext context, String line) {
		println("Actual positions are:");
		Positions positions = context.positions();
		println(positions );
		println(format("\n{0} position(s) found!", positions.size()));
		println("\nTotal Value is:");
		println(positions.value());
	}

	@Override
	public boolean isCalled(String line) {
		return line.startsWith("p");
	}

	@Override
	public String describe() {
		return "	'p' or 'positions'		- print all positions";
	}
}
