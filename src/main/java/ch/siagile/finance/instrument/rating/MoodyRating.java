package ch.siagile.finance.instrument.rating;

import static java.text.MessageFormat.*;

public class MoodyRating extends Rating implements Comparable<MoodyRating> {

	private int id;
	private final String value;

	public MoodyRating(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public MoodyRating(String value) {

		this.value = value;
	}

	public static Rating from(int id, String value) {
		return new MoodyRating(id, value);
	}

	public static MoodyRating from(String value) {
		return new MoodyRating(value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!MoodyRating.class.isInstance(obj))
			return false;
		MoodyRating rating = (MoodyRating) obj;
		if (id != rating.id)
			return false;
		return value.equals(rating.value);
	}

	@Override
	public String toString() {
		return format("Moody Rating id({0}) value:{1}", id, value);
	}

	public int compareTo(MoodyRating o) {
		return -1;
	}
}
