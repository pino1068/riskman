package ch.siagile.finance;

import static java.text.MessageFormat.*;

import java.util.*;

public class Shell {
	
	private static final List<Command> strategies = new LinkedList<Command>();

	public Shell() {
		add(new EquityCommand(""));
		add(new AreaCommand(""));
		add(new CurrencyCommand(""));
		add(new RatingCommand(""));
	}

	private void add(Command command) {
		strategies.add(command);
	}

	public String execute(Positions positions, String definition) {
		if (definition.equals(""))
			return "OK";

		return commandFor(definition).execute(positions);
	}

	private Command commandFor(String definition) {

		for (Command strategy : strategies) {
			if (strategy.canExecute(definition)) {
				return strategy.createFrom(definition);
			}
		}
		throw new RuntimeException(format("unknown command for {0}", definition));
	}

}