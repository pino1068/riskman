package riskman.money;
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

	public boolean isLowerOrEqualsTo(BigDecimal aValue) {
		return divide().compareTo(aValue) <= 0;
	}

	public boolean isGreaterTheOrEqualsTo(BigDecimal aValue) {
		return divide().compareTo(aValue) >= 0;
	}
	
	private BigDecimal divide() {
		return numerator.amount().divide(denominator.amount(), 6, BigDecimal.ROUND_DOWN);
	}

	@Override
	public boolean equals(Object obj) {
		if(isNotRatio(obj)) return false;
		if(!numerator.equals(toRatio(obj).numerator)) return false;
		if(!denominator.equals(toRatio(obj).denominator)) return false;
		return true;
	}

	private Ratio toRatio(Object obj) {
		return (Ratio) obj;
	}

	private boolean isNotRatio(Object obj) {
		return !Ratio.class.isInstance(obj);
	}

	public boolean isEqualsTo(BigDecimal aValue) {
		return divide().compareTo(aValue) == 0;
	}

	@Override
	public String toString() {
		return format("{0} over {1}", numerator, denominator);
	}
	
	@Override
	public int hashCode() {
		return 13 * numerator.hashCode() + 17 * denominator.hashCode();
	}

	public Percent percent() {
		return Percent.from(numerator, denominator);
	}
}
 