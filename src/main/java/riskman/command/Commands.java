package riskman.command;

import java.util.*;

public class Commands {

	private static Map<String, Class<? extends BaseCommand>> commands = new HashMap<String, Class<? extends BaseCommand>>() {
		private static final long serialVersionUID = 1L;
		{
			put("help", HelpCommand.class);
			put("positions", PositionCommand.class);
			put("filter", FilterCommand.class);
			put("load", LoadCommand.class);
			put("check", SplitCheckCommand.class);
			put("split", SplitByCommand.class);
			put("remove", RemoveFilterCommand.class);
			put("quit", QuitCommand.class);
		}
	};

	public List<Command> list() {
		return new LinkedListExtension();
	}

	public Command build(String line) {
		return instanceOf(findClass(extractName(line)));
	}

	private BaseCommand instanceOf(Class<? extends BaseCommand> commandClass) {
		BaseCommand result = new QuitCommand();
		try {
			result = commandClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.init(this);
		return result;
	}

	private String extractName(String line) {
		return line.split(":")[0];
	}

	private Class<? extends BaseCommand> findClass(String definition) {
		for (String name : commands.keySet()) {
			if (canExecute(definition, name))
				return commands.get(name);
		}
		return defaultCommand();
	}

	private boolean canExecute(String definition, String name) {
		return definition.startsWith(name);
	}

	private Class<? extends BaseCommand> defaultCommand() {
		return FilterCheckCommand.class;
	}

	public String contentOf(String definition) {
		for (String name : commands.keySet())
			if (canExecute(definition, name))
				return definition.replaceAll(name+":", "");

		return definition;
	}

	private final class LinkedListExtension extends LinkedList<Command> {
		private static final long serialVersionUID = 1L;
		{
			for (Class<? extends Command> menu2 : commands.values()) {
				Command newInstance = new QuitCommand();
				try {
					newInstance = menu2.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
				add(newInstance);
			}
		}
	}
}
