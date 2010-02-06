package ch.siagile.finance.command;

import static java.text.MessageFormat.*;
import ch.siagile.finance.constraint.*;
import ch.siagile.finance.position.*;

public class FilterCommand extends BaseCommand {

	public void execute(String definition) {
		definition = commands.contentOf(definition);
		println(format("Positions filtered with criteria: <{0}>",
				definition));
		newLine();
		try {
			Positions positions = workspace.positions();
			Positions selected = positions .select(filter(definition));
			println(selected);
			newLine();
			println(format(
					"{0} position(s) selected over {1} with criteria <{2}>",
					selected.size(), positions.size(), definition));
			newLine();
			println("Ratio and Values are:");
			println(selected.divideBy(positions));
			workspace.changeTo(definition, selected);
		} catch (Exception e) {
			print("Error: ", e.getMessage(), "");
		}
	}

	private Filter filter(String definition) {
		return Filter.from(definition);
	}

	public String describe() {
		return "	'filter:<criteria>'		- print positions filtered by given criteria";
	}

}
