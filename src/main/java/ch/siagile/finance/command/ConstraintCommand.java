package ch.siagile.finance.command;

import static java.text.MessageFormat.*;
import ch.siagile.finance.app.*;
import ch.siagile.finance.check.*;
import ch.siagile.finance.constraint.*;
import ch.siagile.finance.position.*;

public class ConstraintCommand extends BaseCommand {

	public ConstraintCommand(String content) {
		super(content);
	}
	
	private String checkFrom(String definition) {
		return definition.substring(definition.lastIndexOf(" ")+1);
	}

	private String check() {
		return checkFrom(content());
	}

	private String failMessage(Positions positions) {
		Positions filtered = constraint().filter(positions);
		
		return format("but is {0}: {1} of {2}", percent(positions, filtered), filtered.value(), positions.value());
	}

	private String percent(Positions positions, Positions filtered) {
		if(positions.isEmpty())
			return "";
		return filtered.percentOf(positions).toString();
	}
	
	private String execute(String dirname, Positions positions) {
		boolean success = constraint().checkLimitOn(positions);
		if (success)
			return format("{0} OK", content());
		return format("{0} KO {1}", content(), failMessage(positions));
	}

	public String execute(Workspace data) {
		return execute(data.workingDir(), data.positions());
	}

	private Constraint constraint() {
		return Constraint.from(filter(filter()),Check.from(check()));
	}

	private String filter() {
		return content().substring(0, content().lastIndexOf(" "));
	}

	private Filter filter(String definition) {
		return Filter.from(definition);
	}

	public String describe() {
		return "	'<filter> <check>'		- apply a constraint to current positions, i.e. equity:CHF max:30%";
	}
}
