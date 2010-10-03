package riskman.parser;

import java.math.BigDecimal;
import java.util.List;

import riskman.money.ExchangeRate;
import riskman.money.ExchangeRates;
import riskman.money.Money;
import riskman.repository.TextRepository;

public class ExchangeRateParser extends CsvLineParser {

	private static final int RATE_AGAINST_CHF = 1;
	private static final int CURRENCY = 0;

	public ExchangeRateParser(String line) {
		super(line);
	}

	public static ExchangeRates load(String fileName) {
		ExchangeRates result = new ExchangeRates();
		List<String> lines = new TextRepository().load(ExchangeRate.class
				.getClassLoader().getResourceAsStream(fileName));
		for (String line : lines)
			result.add(new ExchangeRateParser(line).parse());
		return result;
	}

	public ExchangeRate parse() {
		return new ExchangeRate(Money.from(BigDecimal.ONE, f(CURRENCY)),Money.CHF(fDouble(RATE_AGAINST_CHF)));
	}

}
