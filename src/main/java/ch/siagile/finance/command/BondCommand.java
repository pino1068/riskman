package ch.siagile.finance.command;

import ch.siagile.finance.constraint.*;


public class BondCommand extends TypeCommand {

	public BondCommand(String definition) {
		super(definition, "bond");
	}

	@Override
	public Constraint constraint() {
		return new BondConstraint(check());
	}
}
