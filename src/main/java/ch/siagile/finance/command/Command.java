package ch.siagile.finance.command;

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
		return definition + (result ? " OK" : " KO");
	}

	public abstract Command createFrom(String definition);

}
