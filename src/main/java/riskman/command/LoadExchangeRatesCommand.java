package riskman.command;

import riskman.money.ExchangeRate;
import riskman.money.ExchangeRates;
import riskman.parser.ExchangeRateParser;


public class LoadExchangeRatesCommand extends BaseCommand {

	public void execute(String definition) {
		ExchangeRates.use(ExchangeRateParser.load(cleanUp(definition)));
		println ("Imported " +
				ExchangeRates.size() +
				" exchange rates:");
		for (ExchangeRate rate : ExchangeRates.rates()) 
			println(rate);
		
	}

	private String cleanUp(String definition) {
		if(definition.contains(":"))
			return definition.split(":")[1];
		return definition;
	}

	@Override
	public String describe() {
		return "	'load-exchange-rates:<file>'	- load exchange rates from given file";
	}
	
}
