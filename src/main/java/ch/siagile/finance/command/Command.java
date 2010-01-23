package ch.siagile.finance.command;

import static java.text.MessageFormat.*;
import ch.siagile.finance.constraint.*;
import ch.siagile.finance.position.*;

public abstract class Command {

	private final String definition;

	public Command(String definition) {
		this.definition = definition;
	}

	public Constraint constraint() {
		return new TrueConstraint();
	}

	protected String checkFrom(String definition) {
		return definition.split(" ")[1];
	}

	protected String[] commandValues(String name, String definition) {
		return definition.split(" ")[0].replaceAll(name, "").split(",");
	}

	protected String definition() {
		return definition;
	}

	protected String check() {
		return checkFrom(definition);
	}

	protected String[] values(String name) {
		return commandValues(name, definition());
	}

	public abstract boolean canExecute(String string);

	public String execute(Positions positions) {
		return execute("", positions);
	}

	private String failMessage(Positions allPositions) {
		Positions filtered = constraint().filter(allPositions);

		return format("but is {0} {1} of {2}", filtered.percentOf(allPositions), filtered.value(), allPositions.value());
	}

	public abstract Command createFrom(String definition);

	public String execute(String dirname, Positions positions) {
		boolean success = constraint().checkLimitOn(positions);
		if (success)
			return format("{0} OK", definition);
		return format("{0} KO {1}", definition, failMessage(positions));
	}

}