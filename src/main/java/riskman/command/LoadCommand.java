package riskman.command;

import static riskman.Some.*;
import static riskman.money.Money.*;
import static java.text.MessageFormat.*;

import java.io.File;
import java.util.*;

import riskman.money.*;
import riskman.parser.*;
import riskman.position.*;
import riskman.repository.*;

public class LoadCommand extends BaseCommand {

	private PositionsParser positionsParser = new PositionsParser();
	private List<String> warnings = new LinkedList<String>();
	private int counterLoadedPositions = 0;
	private int counterLinesRead = 0;
	
	public void execute(String definition) {
		loadExchangeRates();
		
		File file = file(definition);
		positionsParser.listener(listeners());
		workspace.addAll(readAndParse(file));
		workspace.console.println(output());
	}

	private void loadExchangeRates() {
		ExchangeRates.clear();
		ExchangeRates.addRate(ExchangeRate.rateFrom(CHF(1),		CHF(1)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(USD(1),		CHF(1.1)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(EUR(1),		CHF(1.3)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(GBP(1),		CHF(1.6)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(AUD(1),		CHF(0.9)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(BRL(1),		CHF(0.6)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(NZD(1),		CHF(0.70)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(JPY(100.0),	CHF(1.20)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(money(1,"CHr"),CHF(1)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(money(10,"SEK"),CHF(1.4)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(money(1,"CAD"),CHF(0.95)));
	}

	private File file(String definition) {
		if(commands!=null)
			definition = commands.contentOf(definition);
		String dirname = workspace.workingDir();
		final String filename = cleanUp(definition);
		if(dirname==null || dirname.length()==0) return new File(filename);
		return new File(dirname, filename);
	}

	private String cleanUp(String definition) {
		if(definition.contains(":"))
			return definition.split(":")[1];
		return definition;
	}

	private LoadParserListener listeners() {
		return new LoadParserListener(new StoreBondDataListener(new StoreOwnerListener()));
	}

	private Positions readAndParse(File file) {
		return positionsParser.parse(readLines(file));
	}

	private List<String> readLines(File file) {
		return new TextRepository().load(file);
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
