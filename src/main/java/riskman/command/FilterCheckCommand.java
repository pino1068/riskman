package riskman.command;

import static java.text.MessageFormat.*;

import java.security.*;

import riskman.constraint.*;
import riskman.position.*;

public class FilterCheckCommand extends CheckCommand {

	public void execute(String definition) {
		if(definition.trim().length()==0)
			return;
		boolean success = checkConstraint(definition);
		if (success)
			println(format("{0} OK", definition));
		else
			println(format("{0} KO {1}", definition, failMessage(definition,workspace.positions())));
	}

	private boolean checkConstraint(String definition) {
		boolean success;
		try {
			success = constraint(definition).checkLimitOn(workspace.positions());
		} catch (Exception e) {
			throw new InvalidParameterException(definition);
		}
		return success;
	}

	private Constraint constraint(String def) {
		return Constraint.from(filter(def),check(def));
	}

	private Filter filter(String def) {
		return Filter.from(filterDefinition(def));
	}

	private String filterDefinition(String definition) {
		return definition.substring(0, definition.lastIndexOf(" "));
	}

	private String failMessage(String definition, Positions positions) {
		Positions filtered = constraint(definition).filter(positions);
		
		return format("but is {0}: {1} of {2}", percent(positions, filtered), filtered.value(), positions.value());
	}

	private String percent(Positions positions, Positions filtered) {
		if(positions.isEmpty())
			return "";
		return filtered.percentOf(positions).toString();
	}

	public String describe() {
		return "	'<filter> <check>'		- apply a constraint to current positions, i.e. equity:CHF max:30%";
	}
}
