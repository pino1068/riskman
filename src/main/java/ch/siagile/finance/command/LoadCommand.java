package ch.siagile.finance.command;

import static ch.siagile.finance.Some.*;
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

	private PositionsParser positionsParser = new PositionsParser();

	public String execute(Positions positions) {
		return execute("", positions);
	}

	public String execute(String dirname, Positions positions) {

		String pathname = pathname(dirname);
		
		List<String> strings = readLines(pathname);

		tryParse(positions, strings);

		return output();
	}

	private void tryParse(Positions positions, List<String> strings) {
		for (String string : strings) {
			tryParse(positions, string);
		}
	}

	private void tryParse(Positions positions, String string) {
		try {
			positions.add(positionsParser.parse(string));
		} catch (Exception e) {
			warning(string);
		}
	}

	private String output() {
		if (hasWarnings())
			return failWith(warnings);

		return success();
	}

	private String success() {
		return message("{0}", "OK");
	}

	private String failWith(List<String> strings) {
		return message("{0}\n{1}", "KO", some(strings).toString());
	}

	private boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	private String message(String format, Object... args) {
		return format("{0} {1}", definition(), format(format, args));
	}

	private void warning(String string) {
		warnings.add(format("warning:{0}", string));
	}

	private String pathname(String dirname) {
		return format("{0}/{1}", dirname, values("load:")[0]).replaceAll("^/*", "");
	}

	private List<String> readLines(String pathname) {
		return new TextRepository().load(pathname);
	}

	@Override
	public Command createFrom(String definition) {
		return new LoadCommand(definition);
	}

}
