package riskman.money;

import java.util.*;

public class ExchangeRates {

	private static Set<ExchangeRate> rates = new HashSet<ExchangeRate>();

	public static void add(ExchangeRate rate) {
		rates.add(rate);
	}

	public static Money changeMoney(Money moneyToChange, String currency) {
		return rate(moneyToChange.currency(),currency).change(moneyToChange);
	}


	private static ExchangeRate rate(String currency1,String currency2) {
		for (ExchangeRate rate : rates) {
			if(rate.canChange(currency1, currency2))
				return rate;
		}
		return null;
	}

}
