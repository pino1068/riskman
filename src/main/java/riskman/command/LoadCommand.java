package riskman.command;

import static riskman.Some.*;
import static java.text.MessageFormat.*;

import java.util.*;

import riskman.parser.*;
import riskman.position.*;
import riskman.repository.*;

public class LoadCommand extends BaseCommand {

	private PositionsParser positionsParser = new PositionsParser();
	private List<String> warnings = new LinkedList<String>();
	private int counterLoadedPositions = 0;
	private int counterLinesRead = 0;

	public void execute(String definition) {
		String pathname = pathname(definition);
		positionsParser.listener(listeners());
		workspace.positions().addAll(readAndParse(pathname));
		workspace.console.println(output());
	}

	private String pathname(String definition) {
		if(commands!=null)
			definition = commands.contentOf(definition);
		String dirname = workspace.workingDir();
		final String filename = cleanFrom("load:", definition);
		if(dirname==null || dirname.length()==0) return filename;
		return format("{0}/{1}", dirname, filename);
	}

	private String cleanFrom(String string, String definition) {
		return definition.replaceAll(string, "");
	}

	private LoadParserListener listeners() {
		return new LoadParserListener(new StoreBondDataListener(new StoreOwnerListener()));
	}

	private Positions readAndParse(String pathname) {
		return positionsParser.parse(readLines(pathname));
	}

	private List<String> readLines(String pathname) {
		return new TextRepository().load(pathname);
	}
	
	private String output() {
		if (hasWarnings())
			return failWith(warnings);

		return success();
	}

	private String success() {
		return message("{0} - {1} positions successfully loaded!", "OK",
				counterLoadedPositions);
	}

	private String failWith(List<String> strings) {
		return message("{0} - {1} positions loaded over {2} lines read.\n{3}", "KO",
				counterLoadedPositions, counterLinesRead,some(strings).toString());
	}

	private boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	private String message(String format, Object... args) {
		return format(" {0}", format(format, args));
	}

	private void warning(String string) {
		warnings.add(format("warning:{0}", string));
	}

	public String describe() {
		return "	'load:<file>'			- load positions from given file";
	}
	
	private class LoadParserListener implements OperationListener {
		private final OperationListener nextListener;

		public LoadParserListener(OperationListener nextListener) {
			this.nextListener = nextListener;
		}

		public void success(String string, Position position) {
			counterLoadedPositions++;
			counterLinesRead++;
			nextListener.success(string, position);
		}

		public void failure(String string) {
			warning(string);
			counterLinesRead++;
			nextListener.failure(string);
		}
	}
}
