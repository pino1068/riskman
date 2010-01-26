package ch.siagile.finance.command;

import ch.siagile.finance.constraint.*;

public class MultiTypeCommand extends Command {
	
	private BondCommand bondCommand;
	private EquityCommand equityCommand;

	public MultiTypeCommand(String definition) {
		super(definition);
		String types = definition.split(" ")[0];
		if(isBond(types))
			this.bondCommand = new BondCommand(definition);
		if(isEquity(types))
			this.equityCommand = new EquityCommand(definition);
	}

	private boolean isEquity(String definition) {
		return definition.contains("equity");
	}

	private boolean isBond(String definition) {
		return definition.contains("bond");
	}

	@Override
	public Constraint constraint() {
		if(this.bondCommand != null)
			return this.bondCommand.constraint();
		if (this.equityCommand != null) {
			return this.equityCommand.constraint();
		}
		return null;//none 
	}

	@Override
	public boolean canExecute(String definition) {
		return isBond(definition) || isEquity(definition);
	}

}
