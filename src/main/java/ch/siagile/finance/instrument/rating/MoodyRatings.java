package ch.siagile.finance.instrument.rating;

import static java.util.Arrays.*;

import java.util.*;


public class MoodyRatings {
	
	private final static List<String> list = asList("Aaa", "Aa1", "Aa2", "Aa3",
			"A1", "A2", "A3", "Baa1", "Baa2", "Baa3", "Ba1", "Ba2", "Ba3",
			"B1", "B2", "B3", "Caa", "Ca", "C", "NA ");
	
	public static MoodyRating find(String value) {
		return new MoodyRating(value);
	}

	public static int indexOf(String value) {
		return list.indexOf(value);
	}

	public static int compare(MoodyRating aRating, MoodyRating anOtherRating) {
		return indexOf(aRating.value) > indexOf(anOtherRating.value) ? 1 : -1;
	}
}
