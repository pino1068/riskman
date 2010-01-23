package ch.siagile.finance.command;

import ch.siagile.finance.constraint.*;

public class EquityCommand extends Command {

	public EquityCommand(String definition) {
		super(definition);
	}

	@Override
	public Constraint constraint() {
		if (notContains(values()))
			return new EquityConstraint(check());

		return new EquityConstraint(check(), values());
	}

	private String[] values() {
		return values(prefix());
	}

	private String prefix() {
		return name() + ":";
	}

	private String name() {
		return "equity";
	}

	private boolean notContains(String[] values) {
		return values.length == 1 && values[0].equals(name());
	}

	public boolean canExecute(String string) {
		return string.startsWith(name());
	}
	
	@Override
	public Command createFrom(String definition) {
		return new EquityCommand(definition);
	}
}
