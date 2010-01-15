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
		if (!Rating.class.isInstance(obj))
			return false;
		Rating rating = (Rating) obj;
		return value.equals(rating.value);
	}

	@Override
	public String toString() {
		return format("Moody Rating value:{0}", value);
	}
}
