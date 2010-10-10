package riskman.money;

import static java.lang.String.*;
import java.util.*;

public class ExchangeRates implements Iterable <ExchangeRate> {
	
	private static ExchangeRates exchangeRates = new ExchangeRates();

	private Set<ExchangeRate> rates = new HashSet<ExchangeRate>();

	public static void addRate(ExchangeRate rate) {
		exchangeRates.add(rate);
	}

	public void add(ExchangeRate rate) {
		rates.add(rate);
	}

	public static Money changeMoney(Money moneyToChange, String currency) {
		return exchangeRates.rate(moneyToChange.currency(),currency).change(moneyToChange);
	}

	private ExchangeRate rate(String curr1,String curr2) {
		for (ExchangeRate rate : rates) 
			if(rate.canChange(curr1, curr2))
				return rate;
		return crossRate(curr1, curr2);
	}

	private ExchangeRate crossRate(String curr1, String curr2) {
		return findRateWith(curr2).cross(findRateWith(curr1));
	}
	
	private ExchangeRate findRateWith(String currency) {
		for (ExchangeRate rate : rates) 
			if(rate.canChange(currency))
				return rate;
		throw new RuntimeException(format("In rates: %1$s is missing rate for: %2$s",rates,currency));
	}

	public static void clear() {
		exchangeRates = new ExchangeRates();
	}

	public static void use(ExchangeRates rates) {
		exchangeRates = rates;
	}

	public static int size() {
		return exchangeRates.rates.size();
	}

	public static ExchangeRates rates() {
		return exchangeRates;
	}

	@Override
	public Iterator<ExchangeRate> iterator() {
		return rates.iterator();
	}

}
