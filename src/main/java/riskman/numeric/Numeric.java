package riskman.numeric;

import java.math.*;

public class Numeric {

	private final BigDecimal numerator;
	private final BigDecimal denominator;

	private Numeric(String text) {
		this(text, "1");
	}

	private Numeric(String num, String denom) {
		this(new BigDecimal(num), new BigDecimal(denom));
	}

	private Numeric(BigDecimal num, BigDecimal denom) {
		this.numerator = num;
		this.denominator = denom;
	}

	public Double toDouble() {
		return numerator.doubleValue();
	}

	public static Numeric $(String text) {
		return new Numeric(text);
	}

	public static Numeric $(Number i) {
		return $(i.toString());
	}

	public static Numeric $infinite() {
		return new Numeric("1", "0");
	}

	public static Numeric $inf() {
		return new Numeric("1", "0");
	}

	public static Numeric $one() {
		return $(1);
	}

	public static Numeric $zero() {
		return $(0);
	}

	public Numeric add(Numeric other) {
		BigDecimal num = numerator.multiply(other.denominator).add(
				denominator.multiply(other.numerator));
		BigDecimal denum = other.denominator.multiply(denominator);
		return new Numeric(num, denum);
	}

	public boolean isInfinite() {
		return BigDecimal.ZERO.equals(denominator)
				&& !BigDecimal.ZERO.equals(numerator);
	}

	public Numeric multiply(Numeric $) {
		return this.divide($.invert());
	}

	public Numeric invert() {
		return new Numeric(denominator, numerator);
	}

	public Numeric divide(Numeric $) {
		if (isInfinite() && $.isInfinite())
			return $one();
		if (isZero() && $.isZero())
			return $one();
		BigDecimal num = numerator.multiply($.denominator);
		BigDecimal denum = denominator.multiply($.numerator);
		return new Numeric(num, denum);
	}

	public Numeric negate() {
		return this.multiply($(-1));
	}

	public Numeric subtract(Numeric $) {
		return add($.negate());
	}

	public Numeric minus(Numeric $) {
		return subtract($);
	}

	public boolean greaterThan(Numeric $) {
		return approximation().compareTo($.approximation()) > 0;
	}

	public boolean gt(Numeric $) {
		return greaterThan($);
	}

	public boolean lowerThan(Numeric $) {
		return !ge($);
	}

	public boolean ge(Numeric $) {
		return greaterThan($) || equals($);
	}

	public boolean lt(Numeric $) {
		return lowerThan($);
	}

	public boolean le(Numeric $) {
		return lowerThan($) || equals($);
	}

	public Numeric times(Number num) {
		return multiply($(num));
	}

	public Numeric mul(Numeric $) {
		return multiply($);
	}

	public Numeric div(Numeric $) {
		return divide($);
	}

	@Override
	public String toString() {
		if (isInfinite())
			return "infinite oo";
		return approximation().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numerator == null) ? 0 : numerator.hashCode());
		result = prime * result
				+ ((denominator == null) ? 0 : denominator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Numeric other = (Numeric) obj;
		if (isNotEqualTo(other))
			return false;
		return true;
	}

	private boolean isNotEqualTo(Numeric other) {
		if (isInfinite())
			return !other.isInfinite();
		if (other.isInfinite())
			return !isInfinite();
		if (isZero())
			return !other.isZero();
		return !isApproxEqual(other);
	}

	private boolean isApproxEqual(Numeric other) {
		return approximation().equals(other.approximation());
	}

	private boolean isZero() {
		return BigDecimal.ZERO.equals(numerator) && !isInfinite();
	}

	private BigDecimal approximation() {
		return numerator().divide(denominator(), 5, RoundingMode.UP);
	}

	private BigDecimal numerator() {
		return scale(numerator, BigDecimal.ZERO);
	}

	private BigDecimal denominator() {
		return scale(denominator, BigDecimal.ONE);
	}

	private BigDecimal scale(BigDecimal value, BigDecimal defaultValue) {
		if (value == null)
			return defaultValue;
		return value.setScale(120, RoundingMode.UP);
	}
}
