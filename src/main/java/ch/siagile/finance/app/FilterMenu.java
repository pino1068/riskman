package ch.siagile.finance.app;

import static java.text.MessageFormat.*;
import ch.siagile.finance.constraint.*;
import ch.siagile.finance.position.*;

public class FilterMenu extends BaseMenu {

	public boolean isCalled(String line) {
		return line.startsWith("f");
	}

	public void perform(Positions positions, String line) {
		println(format("Positions filtered with criteria: <{0}>",
				criteria(line)));
		newLine();
		try {
			Positions selected = positions.select(filter(line));
			println(selected);
			newLine();
			println(format(
					"{0} position(s) selected over {1} with criteria <{2}>",
					selected.size(), positions.size(), criteria(line)));
			newLine();
			println("Ratio and Values are:");
			println(selected.divideBy(positions));
		} catch (Exception e) {
			print("Error: ", e.getMessage(), "");
		}
	}

	private Filter filter(String definition) {
		return Filter.from(criteria(definition));
	}

	private String criteria(String definition) {
		return definition.substring(definition.indexOf(":") + 1);
	}

	public String describe() {
		return "	'f:<criteria>'			- print positions filtered by given criteria";
	}

}
