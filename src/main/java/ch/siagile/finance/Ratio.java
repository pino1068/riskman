package ch.siagile.finance;
import static java.text.MessageFormat.*;

import java.math.*;

public class Ratio {

	private final Money numerator;
	private final Money denominator;

	public Ratio(Money numerator, Money denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public static Ratio from(Money numerator, Money denominator) {
		return new Ratio(numerator, denominator);
	}

	public boolean isLowerOrEqualsTo(BigDecimal bigDecimal) {
		return this.divide().compareTo(bigDecimal) <= 0;
	}

	private BigDecimal divide() {
		return numerator.amount().divide(denominator.amount(), 6, BigDecimal.ROUND_DOWN);
	}

	public boolean isGreaterTheOrEqualsTo(BigDecimal value) {
		return this.divide().compareTo(value) >= 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(!Ratio.class.isInstance(obj)) return false;
		Ratio other = (Ratio)obj;
		if(!this.numerator.equals(other.numerator)) return false;
		if(!this.denominator.equals(other.denominator)) return false;
		return true;
	}

	public boolean isEqualsTo(BigDecimal value) {
		return this.divide().compareTo(value) == 0;
	}

	@Override
	public String toString() {
		return format("{0}/{1}", numerator, denominator);
	}
}
 