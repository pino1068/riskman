package ch.siagile.finance;

public class AreaCommand extends Command {

	public AreaCommand(String definition) {
		super(definition);
	}

	@Override
	public Constraint constraint() {
		return new AreaConstraint(check(), values("area:"));
	}

	public boolean canExecute(String string) {
		return string.startsWith("area");
	}

	@Override
	public Command createFrom(String definition) {
		return new AreaCommand(definition);
	}

}
