package ch.siagile.finance;

public class EquityStrategy extends Command {

	public EquityStrategy(String definition) {
		super(definition);
	}

	@Override
	public Constraint constraint() {
		String[] equities = commandValues("equity:", definition());
		return new EquityConstraint(check());
	}

	@Override
	public boolean canExecute(String string) {
		return string.startsWith("equity");
	}

	@Override
	public Command createFrom(String definition) {
		return new EquityStrategy(definition);
	}
}
