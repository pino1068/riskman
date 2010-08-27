package riskman.money;

public class ExchangeRate {

	private final Money from;
	private final Money to;

	public ExchangeRate(Money from, Money to) {
		this.from = from;
		this.to = to;
	}

	public static ExchangeRate rateFrom(Money from, Money to) {
		return new ExchangeRate(from, to);
	}

	public Money change(Money source) {
		if (source.compatible(from))
			return to.times(source).divideBy(from.amount());
		return from.times(source).divideBy(to.amount());
	}

	public boolean canChange(String aCurrency1, String aCurrency2) {
		return (from.compatible(aCurrency1) && to.compatible(aCurrency2))   ||
		(from.compatible(aCurrency2) && to.compatible(aCurrency1));
	}

}
