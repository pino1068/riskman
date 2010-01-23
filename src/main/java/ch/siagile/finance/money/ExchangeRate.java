package ch.siagile.finance.money;

public class ExchangeRate {

	private final Money from;
	private final Money to;

	public ExchangeRate(Money from, Money to) {
		this.from 	= from;
		this.to 	= to;
	}

	public static ExchangeRate from(Money from, Money to) {
		return new ExchangeRate(from, to);
	}

	public Money change(Money source) {
		if(source.compatible(from))
			return to.times(source).divideBy(from.amount());
		return from.times(source).divideBy(to.amount());
	}

}
