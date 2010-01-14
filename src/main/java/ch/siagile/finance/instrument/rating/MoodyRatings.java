package ch.siagile.finance.instrument.rating;

public class MoodyRatings {

	public static MoodyRating find(String value) {
		return new MoodyRating(1,value);
	}

}
