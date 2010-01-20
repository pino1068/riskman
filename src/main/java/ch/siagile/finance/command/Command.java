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
		boolean result = constraint().checkLimitOn(positions);
		if (result)
			return format("{0} OK", definition);

		return format("{0} KO {1}", definition, failMessage(positions));
	}

	private String failMessage(Positions allPositions) {

		Positions filtered = constraint().filter(allPositions);

		String filteredPercent = "";
		String filteredValue = "";
		String positionsValue = "";

		filteredPercent = filtered.divideBy(allPositions).percent().toString();
		filteredValue = filtered.value().toString();
		positionsValue = allPositions.value().toString();

		return format(" but is {0} {1} of {2}", filteredPercent, filteredValue, positionsValue);
	}

	public abstract Command createFrom(String definition);

}