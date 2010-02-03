package ch.siagile.finance.app;

import static java.text.MessageFormat.*;
import ch.siagile.finance.position.*;

public class PositionMenu extends BaseMenu {

	public void execute(Workspace context, String line) {
		println("Actual positions are:");
		Positions positions = context.positions();
		println(positions );
		println(format("\n{0} position(s) found!", positions.size()));
		println("\nTotal Value is:");
		println(positions.value());
	}

	public boolean canExecute(String line) {
		return line.startsWith("p");
	}

	public String describe() {
		return "	'p' or 'positions'		- print all positions";
	}
}
