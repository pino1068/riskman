package ch.siagile.finance.app;

import static ch.siagile.finance.Some.*;
import static java.text.MessageFormat.*;

import java.util.*;

import ch.siagile.finance.command.*;
import ch.siagile.finance.position.*;

public class Shell {

	private static final List<Command> commands = new LinkedList<Command>() {
		{
			add(new EquityCommand(""));
			add(new AreaCommand(""));
			add(new CurrencyCommand(""));
			add(new RatingCommand(""));
			add(new LoadCommand(""));
		}
	};

	public String execute(Positions positions, String definition) {
		return execute("", positions, definition);
	}

	private String execute(String dirname, Positions positions, String definition) {
		if (definition.equals(""))
			return "OK";

		return commandFor(definition).execute(dirname, positions);
	}

	private Command commandFor(String definition) {

		for (Command command : commands) {
			if (command.canExecute(definition)) {
				return command.createFrom(definition);
			}
		}
		throw new RuntimeException(format("unknown command for {0}", definition));
	}

	public String execute(Positions positions, List<String> definitions) {
		return execute("", positions, definitions);
	}

	public String execute(String dirname, Positions positions, List<String> definitions) {
		List<String> outputs = new LinkedList<String>();
		for(String definition: definitions) {
			try {
				outputs.add(execute(dirname, positions, definition));
			} catch (Exception e) {
				return format("error:{0} {1}", definition, e.getMessage());
			}
		}
		return some(outputs).toString();
	}

}