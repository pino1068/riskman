package riskman.money;

import static java.lang.String.format;

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

	public boolean canChange(String aCurrency) {
		return from.compatible(aCurrency) || to.compatible(aCurrency);
	}

	public ExchangeRate cross(ExchangeRate aRate2) {
		return rateFrom(crossFrom(aRate2), crossTo(aRate2));
	}

	private Money crossTo(ExchangeRate aRate2) {
		return aRate2.from.times(to);
	}

	private Money crossFrom(ExchangeRate aRate2) {
		return from.times(aRate2.to);
	}
	
	@Override
	public String toString() {
		return format("Exchange from %1$s to %2$s", from, to);
	}

}
