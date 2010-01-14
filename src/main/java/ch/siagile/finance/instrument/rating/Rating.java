package ch.siagile.finance.instrument.rating;

public class Rating {

	public static Rating NotRated(){
		return MoodyRating.from(30,"NR");
	}

}
