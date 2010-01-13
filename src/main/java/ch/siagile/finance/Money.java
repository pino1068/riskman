package ch.siagile.finance;

import java.math.*;

public class Money {
	private String currency;
	private BigDecimal amount;

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
		if (!this.currency.equals(other.currency))
			return false;
		return this.amount.compareTo(other.amount) == 0;
	}

	public Money times(BigDecimal quantity) {
		return new Money(amount.multiply(quantity), this.currency);
	}

	public Money times(double quantity) {
		return times(BigDecimal.valueOf(quantity));
	}

	public Money plus(Money balance) {
		if (this.currency != balance.currency)
			throw new RuntimeException("adding differing currencies is not allowed");
		return Money.from((amount.add(balance.amount)), currency);
	}

	@Override
	public String toString() {
		return "Money: " + this.amount + " " + this.currency;
	}

	public Ratio divideBy(Money other) {
		return Ratio.from(this, other);
	}

	public boolean compatible(Money other) {
		return compatible(other.currency);
	}

	public BigDecimal amount() {
		return this.amount;
	}

	public Money divideBy(BigDecimal amount) {
		return Money.from(this.amount.divide(amount), currency);
	}

	public boolean compatible(String currency) {
		return this.currency.equals(currency);
	}

}
