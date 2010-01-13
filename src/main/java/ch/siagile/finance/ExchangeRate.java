package ch.siagile.finance;

public class ExchangeRate {

	private final Money from;
	private final Money to;

	public ExchangeRate(Money fromMoney, Money toMoney) {
		this.from = fromMoney;
		this.to = toMoney;
	}

	public static ExchangeRate from(Money fromMoney, Money toMoney) {
		return new ExchangeRate(fromMoney, toMoney);
	}

	public Money change(Money source) {
		if (source.compatible(from))
			return to.times(source.amount()).divideBy(from.amount());
		return from.times(source.amount()).divideBy(to.amount());
	}

}
