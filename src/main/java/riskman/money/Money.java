package riskman.money;

import static java.text.MessageFormat.*;

import java.math.*;

public class Money {
	private final String currency;
	private final BigDecimal amount;

	public Money(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public static Money from(double amount, String currency) {
		return from(BigDecimal.valueOf(amount), currency);
	}

	public static Money from(BigDecimal amount, String currency) {
		return new Money(amount, currency);
	}

	@Override
	public boolean equals(Object obj) {
		if (isNotMoney(obj))
			return false;
		if (isNotCompatible(obj))
			return false;
		return compareTo(toMoney(obj)) == 0;
	}

	private int compareTo(Money money) {
		if (isPraticallyEquals(money))
			return 0;
		return amount.compareTo(money.amount);
	}

	private boolean isPraticallyEquals(Money money) {
		return amount.subtract(money.amount).doubleValue() < 0.001;
	}

	private boolean isNotCompatible(Object obj) {
		return !compatible(toMoney(obj));
	}

	private Money toMoney(Object obj) {
		return ((Money) obj);
	}

	private boolean isNotMoney(Object obj) {
		return !Money.class.isInstance(obj);
	}

	public Money times(BigDecimal quantity) {
		return new Money(amount.multiply(quantity), currency);
	}

	public Money times(double quantity) {
		return times(BigDecimal.valueOf(quantity));
	}

	public Money plus(Money other) {
		if (isNotCompatible(other))
			return changeAndSum(other);
		return Money.from((amount.add(other.amount)), currency);
	}

	private Money changeAndSum(Money other) {
		return change(other).plus(this);
	}

	private Money change(Money other) {
		ExchangeRate rate = ExchangeRate.from(Money.from(1, "USD"), Money.from(1.1, "CHF"));
		return rate.change(other);
	}

	@Override
	public String toString() {
		return format("{0} {1}", amount, currency);
	}

	public Ratio divideBy(Money other) {
		return Ratio.from(this, other);
	}

	public boolean compatible(Money other) {
		return compatible(other.currency);
	}

	public BigDecimal amount() {
		return amount;
	}

	public Money divideBy(BigDecimal anAmount) {
		return Money.from(amount.divide(anAmount, 6, RoundingMode.DOWN), currency);
	}

	public boolean compatible(String aCurrency) {
		return currency.equals(aCurrency);
	}

	public Money divideBy(double amount) {
		return divideBy(BigDecimal.valueOf(amount));
	}

	@Override
	public int hashCode() {
		return 13 * amount.hashCode() + 17 * currency.hashCode();
	}

	public Money times(Percent percent) {
		return Money.from(percent.times(amount), currency);
	}

	public Money plus(Percent percent) {
		return Money.from(percent.plus(amount), currency);
	}

	public static Money CHF(double amount) {
		return Money.from(BigDecimal.valueOf(amount), "CHF");
	}

	public static Money USD(double amount) {
		return Money.from(BigDecimal.valueOf(amount), "USD");
	}

	public static Money EUR(double amount) {
		return Money.from(BigDecimal.valueOf(amount), "EUR");
	}

	public Money times(Money other) {
		return times(other.amount);
	}

	public String currency() {
		return currency;
	}
}