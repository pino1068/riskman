package riskman.command;

import static java.text.MessageFormat.*;
import riskman.*;
import riskman.position.*;
import riskman.splitter.*;

public class SplitByCommand extends BaseCommand {

	public void execute(String definition) {
		definition = commands.contentOf(definition);
		String criteria = criteria(definition);
		println(format("Positions splitted by : <{0}>", criteria));
		newLine();
		try {
			Positions positions = workspace.positions();
			Splitter splitter = SplitterBuilder.from(criteria);
			Split split = splitter.split(positions);
			printSplit(criteria, split);
		} catch (Exception e) {
			print("Error: ", e.getMessage(), "");
		}
	}

	private void printSplit(String criteria, Split split) {
		for (Object group : split.groups()) 
			printGroup(criteria, split, group);
		newLine();
	}

	private void printGroup(String criteria, Split split, Object group) {
		Positions positions = split.positions(group);
		println(format("\n----------------- {0}={1};", criteria, group));
		println(positions);
		println(format("{0} position(s) found for group: <{1}>", positions
				.size(), group));
	}
	
	private String criteria(String definition) {
		return definition.substring(definition.indexOf(":") + 1);
	}

	public String describe() {
		return "	'split:<criteria>'		- print the splitted groups by given criteria";
	}

}
