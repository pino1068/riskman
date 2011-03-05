package riskman.numeric;

import java.math.*;

public class VeryFastNumeric extends Numeric{

	private static final String ZERO_S = "0";
	private static final String DOT = ".";
	private static final String DOT_REGEXP = "\\"+DOT;
	private static final int DEFAULT_SCALE = 15;
	
	private final Double value;

	private VeryFastNumeric(String num) {
		this(Double.valueOf(num));
	}

	private VeryFastNumeric(Double num) {
		super((BigDecimal)null,(BigDecimal)null);
		value = num;
	}

	public Double toDouble() {
		return value;
	}

	public static VeryFastNumeric $(String text) {
		return new VeryFastNumeric(text);
	}

	public static VeryFastNumeric _(Number i) {
		return $(i.toString());
	}

	public static VeryFastNumeric _infinite() {
		return new VeryFastNumeric(Double.MAX_VALUE);
	}

	public static VeryFastNumeric _inf() {
		return _infinite();
	}

	public static VeryFastNumeric _one() {
		return _(1);
	}

	public static VeryFastNumeric _zero() {
		return _(0);
	}

	public VeryFastNumeric add(VeryFastNumeric $) {
		return new VeryFastNumeric(value+$.value);
	}

	public boolean isInfinite() {
		return value.equals(Double.MAX_VALUE);
	}

	public VeryFastNumeric multiply(VeryFastNumeric $) {
		return this.divide($.invert());
	}

	public VeryFastNumeric invert() {
		if(isZero())
			return _inf();
		if(isInfinite())
			return _zero();
		return new VeryFastNumeric(1.0/value);
	}

	public VeryFastNumeric divide(VeryFastNumeric $) {
		if (isInfinite() && $.isInfinite())
			return _one();
		if (isZero() && $.isZero())
			return _one();
		if($.isZero())
			return _inf();
		if($.isInfinite())
			return _zero();
		return new VeryFastNumeric(value/$.value);
	}

	public VeryFastNumeric negate() {
		return this.multiply(_(-1));
	}

	public VeryFastNumeric subtract(VeryFastNumeric $) {
		return add($.negate());
	}

	public VeryFastNumeric minus(VeryFastNumeric $) {
		return subtract($);
	}

	public boolean greaterThan(VeryFastNumeric $) {
		return scaled().compareTo($.scaled()) > 0;
	}

	public boolean gt(VeryFastNumeric $) {
		return greaterThan($);
	}

	public boolean lowerThan(VeryFastNumeric $) {
		return !ge($);
	}

	public boolean ge(VeryFastNumeric $) {
		return greaterThan($) || equals($);
	}

	public boolean lt(VeryFastNumeric $) {
		return lowerThan($);
	}

	public boolean le(VeryFastNumeric $) {
		return lowerThan($) || equals($);
	}

	public VeryFastNumeric times(Number num) {
		return multiply(_(num));
	}

	public VeryFastNumeric mul(VeryFastNumeric $) {
		return multiply($);
	}

	public VeryFastNumeric div(VeryFastNumeric $) {
		return divide($);
	}

	@Override
	public String toString() {
		if (isInfinite())
			return "infinite oo";
		final String string = scaled().toString();
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
				+ (value.hashCode());
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
		VeryFastNumeric other = (VeryFastNumeric) $;
		if (isNotEqualTo(other))
			return false;
		return true;
	}

	private boolean isNotEqualTo(VeryFastNumeric $) {
		if (isInfinite())
			return !$.isInfinite();
		if ($.isInfinite())
			return !isInfinite();
		if (isZero())
			return !$.isZero();
		return !isEqualTo($);
	}

	private boolean isEqualTo(VeryFastNumeric $) {
		return scaled().compareTo($.scaled()) == 0;
	}

	private Double scaled() {
		return scale(value,DEFAULT_SCALE);
	}

	private boolean isZero() {
		return value==0;
	}

	public VeryFastNumeric scaleTo(int scale) {
		return new VeryFastNumeric(scale(value,scale));
	}

	private double scale(Double value, int scale) {
		final double powOfTens = Math.pow(10,scale);
		double v = Math.round(value*powOfTens);
		return v/powOfTens;
	}
}
