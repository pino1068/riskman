package ch.siagile.finance.command;

import static java.text.MessageFormat.*;

import java.util.*;

import ch.siagile.finance.*;
import ch.siagile.finance.position.*;
import ch.siagile.finance.repository.*;

public class LoadCommand extends Command {

	private List<String> warnings = new LinkedList<String>();

	public LoadCommand(String definition) {
		super(definition);
	}

	@Override
	public boolean canExecute(String string) {
		return string.startsWith("load");
	}

	public String execute(Positions positions) {
		PositionsParser positionsParser = new PositionsParser();
		for (String string : lines(pathname())) {
			try {
				positions.add(positionsParser.parse(string));
			} catch (Exception e) {
				warning(string);
			}
		}

		if (!warnings.isEmpty())
			return format("{0} {1}\n{2}", definition(), "KO", toString(warnings));
		return format("{0} {1}", definition(), "OK");
	}

	private String toString(List<String> strings) {
		StringBuilder builder = new StringBuilder();
		for (String string : strings) {
			builder.append(string).append("\n");
		}
		return builder.toString();
	}

	private void warning(String string) {
		warnings.add(format("warning:{0}", string));
	}

	private String pathname() {
		return values("load:")[0];
	}

	private List<String> lines(String pathname) {
		return new TextRepository().load(pathname);
	}

	@Override
	public Command createFrom(String definition) {
		return new LoadCommand(definition);
	}

}
