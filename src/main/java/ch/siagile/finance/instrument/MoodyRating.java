package ch.siagile.finance.instrument;


public class MoodyRating extends Rating implements Comparable<MoodyRating> {

	public MoodyRating(String value) {
		super(value);
	}

	public static MoodyRating from(String value) {
		return new MoodyRating(value);
	}

	public int compareTo(MoodyRating other) {
		return MoodyRatings.compare(other, this);
	}
}
