package ch.siagile.finance.money;

import static java.text.MessageFormat.*;

import java.math.*;


public class Percent {

	private final double value;

	public Percent(String string) {
		double myValue = 0;
		try {
			myValue = Double.valueOf(string.split("%")[0]);
		} catch (NumberFormatException e) {
		}
		value = myValue;
	}

	public Percent(BigDecimal value) {
		this.value = value.doubleValue();
	}

	public static Percent from(String string) {
		return new Percent(string);
	}

	public Double value() {
		return value;
	}

	public BigDecimal times(BigDecimal amount) {
		return amount.multiply(toBigDecimal());
	}

	public BigDecimal toBigDecimal() {
		return BigDecimal.valueOf(value).divide(BigDecimal.valueOf(100));
	}

	public boolean isGreaterThen(Percent anOther) {
		return value > anOther.value;
	}

	public BigDecimal plus(BigDecimal amount) {
		return amount.add(amount.multiply(toBigDecimal()));
	}

	public static Percent from(Money numerator, Money denominator) {
		return new Percent(numerator.amount().divide(denominator.amount(), 6, RoundingMode.DOWN).multiply(BigDecimal.valueOf(100)));
	}
	
	@Override
	public String toString() {
		return format("{0}%", value);
	}

}
