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


	private static ExchangeRate rate(String curr1,String curr2) {
		for (ExchangeRate rate : rates) 
			if(rate.canChange(curr1, curr2))
				return rate;
		return crossRate(curr1, curr2);
	}

	private static ExchangeRate crossRate(String curr1, String curr2) {
		return findRateWith(curr2).cross(findRateWith(curr1));
	}
	
	private static ExchangeRate findRateWith(String currency) {
		for (ExchangeRate rate : rates) 
			if(rate.canChange(currency))
				return rate;
		return null;
	}

	public static void clear() {
		rates = new HashSet<ExchangeRate>();
	}

}
