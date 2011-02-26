package riskman.numeric;

import static java.lang.Math.max;
import static java.math.RoundingMode.UP;

import java.math.*;

public class Numeric {

	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.UP;
	private static final int MIN_SCALE = 120;
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

	public Numeric add(Numeric $) {
		BigDecimal num = numerator.multiply($.denominator).add(
				denominator.multiply($.numerator));
		BigDecimal denum = $.denominator.multiply(denominator);
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
		final String string = realDivision().toString();
		String lastZeroRemoved = string.split(".")[1];
		while(lastZeroRemoved.endsWith("0"))
			lastZeroRemoved = lastZeroRemoved.substring(0, lastZeroRemoved.length()-1);
		return string.split(".")[0]+"."+lastZeroRemoved;
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
	public boolean equals(Object $) {
		if (this == $)
			return true;
		if ($ == null)
			return false;
		if (getClass() != $.getClass())
			return false;
		Numeric other = (Numeric) $;
		if (isNotEqualTo(other))
			return false;
		return true;
	}

	private boolean isNotEqualTo(Numeric $) {
		if (isInfinite())
			return !$.isInfinite();
		if ($.isInfinite())
			return !isInfinite();
		if (isZero())
			return !$.isZero();
		return !isEqualTo($);
	}

	private boolean isEqualTo(Numeric $) {
		return realDivision().compareTo($.realDivision())==0;
	}

	private BigDecimal realDivision() {
		return numerator.divide(denominator, maxScale(), UP);
	}

	private int maxScale() {
		final int max = max(numerator.scale(), denominator.scale());
		if (max <= MIN_SCALE)
			return MIN_SCALE;
		return max;
	}

	private boolean isZero() {
		return BigDecimal.ZERO.equals(numerator) && !isInfinite();
	}

	private BigDecimal approximation() {
		return numerator().divide(denominator(), 5, DEFAULT_ROUNDING_MODE);
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
		return scale(value, 120);
	}

	private BigDecimal scale(BigDecimal value, final int targetScale) {
		return scaleToLevel(value, max(targetScale, MIN_SCALE));
	}

	private BigDecimal scaleToLevel(BigDecimal value, final int scaleLevel) {
		return value.setScale(scaleLevel, DEFAULT_ROUNDING_MODE);
	}

	public Numeric scaleTo(int targetScale) {
		return new Numeric(scale(numerator,targetScale),scale(denominator,targetScale));
	}
}
