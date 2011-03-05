package riskman.numeric;

import java.math.*;

public class FastNumeric extends Numeric{

	private static final String ZERO_S = "0";
	private static final String DOT = ".";
	private static final String DOT_REGEXP = "\\"+DOT;
	private static final int DEFAULT_SCALE = 15;
	
	private final Double numerator;
	private final Double denominator;

	private FastNumeric(String text) {
		this(text, "1");
	}

	private FastNumeric(String num, String denom) {
		this(Double.valueOf(num), Double.valueOf(denom));
	}

	private FastNumeric(Double num, Double denom) {
		super((BigDecimal)null,(BigDecimal)null);
		this.numerator = num;
		this.denominator = denom;
	}

	public Double toDouble() {
		return numerator.doubleValue();
	}

	public static FastNumeric $(String text) {
		return new FastNumeric(text);
	}

	public static FastNumeric _(Number i) {
		return $(i.toString());
	}

	public static FastNumeric _infinite() {
		return new FastNumeric("1", ZERO_S);
	}

	public static FastNumeric _inf() {
		return new FastNumeric("1", ZERO_S);
	}

	public static FastNumeric _one() {
		return _(1);
	}

	public static FastNumeric _zero() {
		return _(0);
	}

	public FastNumeric add(FastNumeric $) {
		Double num = (numerator*$.denominator)+(
				denominator*$.numerator);
		Double denum = $.denominator*denominator;
		return new FastNumeric(num, denum);
	}

	public boolean isInfinite() {
		return denominator==0
				&& numerator!=0;
	}

	public FastNumeric multiply(FastNumeric $) {
		return this.divide($.invert());
	}

	public FastNumeric invert() {
		return new FastNumeric(denominator, numerator);
	}

	public FastNumeric divide(FastNumeric $) {
		if (isInfinite() && $.isInfinite())
			return _one();
		if (isZero() && $.isZero())
			return _one();
		Double num = numerator*$.denominator;
		Double denum = denominator*$.numerator;
		return new FastNumeric(num, denum);
	}

	public FastNumeric negate() {
		return this.multiply(_(-1));
	}

	public FastNumeric subtract(FastNumeric $) {
		return add($.negate());
	}

	public FastNumeric minus(FastNumeric $) {
		return subtract($);
	}

	public boolean greaterThan(FastNumeric $) {
		return realDivision().compareTo($.realDivision()) > 0;
	}

	public boolean gt(FastNumeric $) {
		return greaterThan($);
	}

	public boolean lowerThan(FastNumeric $) {
		return !ge($);
	}

	public boolean ge(FastNumeric $) {
		return greaterThan($) || equals($);
	}

	public boolean lt(FastNumeric $) {
		return lowerThan($);
	}

	public boolean le(FastNumeric $) {
		return lowerThan($) || equals($);
	}

	public FastNumeric times(Number num) {
		return multiply(_(num));
	}

	public FastNumeric mul(FastNumeric $) {
		return multiply($);
	}

	public FastNumeric div(FastNumeric $) {
		return divide($);
	}

	@Override
	public String toString() {
		if (isInfinite())
			return "infinite oo";
		String res=numerator+"/"+denominator+"=";
		final String string = realDivision().toString();
		return res+numerator(string) + DOT + decimals(string);
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
		FastNumeric other = (FastNumeric) $;
		if (isNotEqualTo(other))
			return false;
		return true;
	}

	private boolean isNotEqualTo(FastNumeric $) {
		if (isInfinite())
			return !$.isInfinite();
		if ($.isInfinite())
			return !isInfinite();
		if (isZero())
			return !$.isZero();
		return !isEqualTo($);
	}

	private boolean isEqualTo(FastNumeric $) {
//		System.out.println(realDivision() +"/"+$.realDivision());
//		System.out.println(realDivision().compareTo($.realDivision()));
		return realDivision().compareTo($.realDivision()) == 0;
	}

	private Double realDivision() {
		return scale(numerator,DEFAULT_SCALE)/scale(denominator,DEFAULT_SCALE);
	}

	private boolean isZero() {
		return numerator==0&& !isInfinite();
	}

	public FastNumeric scaleTo(int scale) {
		return new FastNumeric(scale(numerator/denominator,scale), 1.0);
	}

	private double scale(Double value, int scale) {
		final double powOfTens = Math.pow(10,scale);
		double v = Math.round(value*powOfTens)+0.0;
		return v/powOfTens;
	}
}
