package ch.siagile.finance.instrument.rating;

public class Rating {

	public static Rating NR(){
		return MoodyRating.from(30,"NR");
	}

}
