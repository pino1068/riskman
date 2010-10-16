package riskman.command;

import static java.text.MessageFormat.*;
import riskman.position.*;

public class PositionCommand extends BaseCommand {

	public void execute(String definition) {
		definition = commands.contentOf(definition);
		Positions positions = workspace.positions();
		if(positions.isEmpty())
			println("Currently there are no positions available. Please, run \"> load:<filename of the CSV>\" for loading it.");
		else{
			println("Actual positions are:");
			println(positions );
			println(format("\n{0} position(s) found!", positions.size()));
			println("\nTotal Value is:");
			println(positions.value());
		}
	}

	public String describe() {
		return "	'positions'			- print all positions";
	}
}
