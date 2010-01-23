package ch.siagile.finance.command;

import ch.siagile.finance.constraint.*;


public class EquityCommand extends TypeCommand {

	public EquityCommand(String definition) {
		super(definition,"equity");
	}

	@Override
	public Constraint constraint() {
		if (notContains(values()))
			return new EquityConstraint(check());

		return new EquityConstraint(check(), values());
	}
}
