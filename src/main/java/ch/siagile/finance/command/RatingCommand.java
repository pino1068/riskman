package ch.siagile.finance.command;

import ch.siagile.finance.constraint.*;

public class RatingCommand extends Command {

	public RatingCommand(String definition) {
		super(definition);
	}

	@Override
	public Constraint constraint() {
		return new RatingConstraint(value("rating:"),check());
	}
	
	private String value(String name) {
		return definition().split(" ")[0].replaceAll(name, "");
	}

	public boolean canExecute(String string) {
		return string.startsWith("rating:");
	}

	@Override
	public Command createFrom(String definition) {
		return new RatingCommand(definition);
	}
}
