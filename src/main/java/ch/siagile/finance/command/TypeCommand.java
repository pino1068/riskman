package ch.siagile.finance.command;

public abstract class TypeCommand extends Command {

	private final String type;

	public TypeCommand(String definition, String type) {
		super(definition);
		this.type = type;
	}

	public TypeCommand(String definition) {
		this(definition, extractType(definition));
	}

	private static String extractType(String definition) {
		String[] split = definition.split(" |:");
		System.out.println("--------"+definition+" -> "+split[0]);
		return split.length > 0 ? split[0] : null;
	}

	protected String[] values() {
		return values(prefix());
	}

	private String prefix() {
		return type + ":";
	}

	protected boolean notContains(String[] values) {
		return values.length == 1 && values[0].equals(type);
	}

	public boolean canExecute(String string) {
		return type.length() > 0 && string.startsWith(type);
	}
}
