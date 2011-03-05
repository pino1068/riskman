package riskman.numeric;

import static java.lang.Math.max;
import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.UP;

import java.math.*;

public class Numeric {

	private static final String ZERO_S = "0";
	private static final String DOT = ".";
	private static final String DOT_REGEXP = "\\"+DOT;
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

	protected Numeric(BigDecimal num, BigDecimal denom) {
		this.numerator = num;
		this.denominator = denom;
	}

	public Double toDouble() {
		return numerator.doubleValue();
	}

	public static Numeric _(String text) {
		return new Numeric(text);
	}

	public static Numeric _(Number i) {
		return _(i.toString());
	}

	public static Numeric _infinite() {
		return new Numeric("1", ZERO_S);
	}

	public static Numeric _inf() {
		return new Numeric("1", ZERO_S);
	}

	public static Numeric _one() {
		return _(1);
	}

	public static Numeric _zero() {
		return _(0);
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
			return _one();
		if (isZero() && $.isZero())
			return _one();
		BigDecimal num = numerator.multiply($.denominator);
		BigDecimal denum = denominator.multiply($.numerator);
		return new Numeric(num, denum);
	}

	public Numeric negate() {
		return this.multiply(_(-1));
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
		return multiply(_(num));
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
		return numerator(string) + DOT + decimals(string);
	}

	private String numerator(final String string) {
		return string.split(DOT_REGEXP)[0];
	}

	private String decimals(final String string) {
		final String[] splitByDOT = string.split(DOT_REGEXP);
		if(splitByDOT.length<2)
			return ZERO_S;
		String lastZeroRemoved = splitByDOT[1];
		while (lastZeroRemoved.endsWith(ZERO_S))
			lastZeroRemoved = reduceOfOne(lastZeroRemoved);
		return lastZeroRemoved.length() == 0 ? ZERO_S
				: lastZeroRemoved;
	}

	private String reduceOfOne(String lastZeroRemoved) {
		lastZeroRemoved = lastZeroRemoved.substring(0,
				lastZeroRemoved.length() - 1);
		return lastZeroRemoved;
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
		return realDivision().compareTo($.realDivision()) == 0;
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

	private BigDecimal scale(BigDecimal value, final int level) {
		return scaleToLevel(value, max(level, MIN_SCALE));
	}

	private BigDecimal scaleToLevel(BigDecimal value, final int level) {
		return value.setScale(level, DEFAULT_ROUNDING_MODE);
	}

	public Numeric scaleTo(int scale) {
		return new Numeric(numerator.divide(denominator, scale, DEFAULT_ROUNDING_MODE), ONE);
	}
}
