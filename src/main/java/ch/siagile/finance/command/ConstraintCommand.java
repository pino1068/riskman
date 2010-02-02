package ch.siagile.finance.command;

import static java.text.MessageFormat.*;
import ch.siagile.finance.check.*;
import ch.siagile.finance.constraint.*;
import ch.siagile.finance.position.*;

public class ConstraintCommand extends Command {

	public ConstraintCommand(String definition) {
		super(definition);
	}
	
	public boolean canExecute(String string) {
		return true;
	}
//TODO should delete this method?
	public Command createFrom(String definition) {
		return new ConstraintCommand(definition);
	}

	private String checkFrom(String definition) {
		return definition.substring(definition.lastIndexOf(" ")+1);
	}

	private String check() {
		return checkFrom(content());
	}
	public String execute(Positions positions) {
		return execute("", positions);
	}

	private String failMessage(Positions allPositions) {
		Positions filtered = constraint().filter(allPositions);

		return format("but is {0} {1} of {2}", filtered.percentOf(allPositions), filtered.value(), allPositions.value());
	}
	
	public String execute(String dirname, Positions positions) {
		boolean success = constraint().checkLimitOn(positions);
		if (success)
			return format("{0} OK", content());
		return format("{0} KO {1}", content(), failMessage(positions));
	}

	private Constraint constraint() {
		return Constraint.from(filter(filter()),Check.from(check()));
	}

	private String filter() {
		return content().substring(0, content().lastIndexOf(" "));
	}

	private Filter filter(String definition2) {
		return Filter.from(definition2);
	}
}
