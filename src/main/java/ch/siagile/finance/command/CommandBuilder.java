package ch.siagile.finance.command;

import static java.text.MessageFormat.*;

import java.util.*;

public class CommandBuilder {

	private static HashMap<String, Class<? extends Command>> commands = new HashMap<String, Class<? extends Command>>() {
		private static final long serialVersionUID = 1L;
		{
			put("load", LoadCommand.class);
			put("splitby", SplitCommand.class);
		}
	};;

	public Command build(String definition) {
		for (String name : commands.keySet()) {
			if (definition.startsWith(name))
				return build(commands.get(name), definition.substring(definition.indexOf(":")+1));
		}
		return build(ConstraintCommand.class,definition);
	}

	private Command build(Class<? extends Command> commandClass,
			String definition) {
		try {
			return commandClass.getConstructor(String.class).newInstance(
					definition);
		} catch (Exception e) {
			throw new RuntimeException(format(
					"command <{0}> not found! Try again, please.", definition),
					e);
		}
	}

}
