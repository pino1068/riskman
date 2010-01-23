package ch.siagile.finance.instrument.rating;

import static java.text.MessageFormat.*;

public class Rating {

	protected final String value;

	public Rating(String value) {
		this.value = value;
	}

	public static Rating NotRated() {
		return MoodyRating.from("NR");
	}

	public boolean isValue(String aValue) {
		return value.equals(aValue);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (isNotRating(obj))	return false;
		return value.equals(toRating(obj).value);
	}

	private Rating toRating(Object obj) {
		return (Rating) obj;
	}

	private boolean isNotRating(Object obj) {
		return !Rating.class.isInstance(obj);
	}

	@Override
	public String toString() {
		return format("rating:{0}", value);
	}
}
