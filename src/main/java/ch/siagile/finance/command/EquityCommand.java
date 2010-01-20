package ch.siagile.finance.command;

import ch.siagile.finance.constraint.*;

public class EquityCommand extends Command {

	public EquityCommand(String definition) {
		super(definition);
	}

	@Override
	public Constraint constraint() {
		String[] values = values("equity:");
		
		if (notContains(values))
			return new EquityConstraint(check());

		return new EquityConstraint(check(), values);
	}

	private boolean notContains(String[] values) {
		return values.length == 1 && values[0].equals("equity");
	}

	public boolean canExecute(String string) {
		return string.startsWith("equity");
	}
	
	@Override
	public Command createFrom(String definition) {
		return new EquityCommand(definition);
	}
}
