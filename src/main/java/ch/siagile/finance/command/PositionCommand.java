package ch.siagile.finance.command;

import static java.text.MessageFormat.*;
import ch.siagile.finance.position.*;

public class PositionCommand extends BaseCommand {

	public void execute(String definition) {
		definition = commands.contentOf(definition);
		println("Actual positions are:");
		Positions positions = workspace.positions();
		println(positions );
		println(format("\n{0} position(s) found!", positions.size()));
		println("\nTotal Value is:");
		println(positions.value());
	}

	public String describe() {
		return "	'p' or 'positions'		- print all positions";
	}
}
