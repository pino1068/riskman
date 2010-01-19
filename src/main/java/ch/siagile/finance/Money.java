package ch.siagile.finance;

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
		if (!Money.class.isInstance(obj))
			return false;
		Money other = (Money) obj;
		if (!currency.equals(other.currency))
			return false;
		return amount.compareTo(other.amount) == 0;
	}

	public Money times(BigDecimal quantity) {
		return new Money(amount.multiply(quantity), currency);
	}

	public Money times(double quantity) {
		return times(BigDecimal.valueOf(quantity));
	}

	public Money plus(Money other) {
		if (!compatible(other)) {
			return changeAndSum(other);
		}
		return Money.from((amount.add(other.amount)), currency);
	}

	private Money changeAndSum(Money other) {
		return change(other).plus(this);
	}

	private Money change(Money other) {
		ExchangeRate rate = ExchangeRate.from(Money.from(1, "USD"),Money.from(1.1, "CHF"));
		Money otherChf = rate.change(other);
		return otherChf;
	}

	@Override
	public String toString() {
		return format("Money: {0} {1}", amount, currency);
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
		return Money.from(amount.divide(anAmount), currency);
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
}
