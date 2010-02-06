package riskman.instrument.rating;

import static java.text.MessageFormat.*;
import static java.util.Arrays.*;

import java.util.*;

public class MoodyRatings {

	private final static List<String> list = asList("Aaa", "Aa1", "Aa2", "Aa3", "A1", "A2", "A3", "Baa1", "Baa2", "Baa3", "Ba1", "Ba2", "Ba3", "B1", "B2", "B3", "Caa", "Ca", "C", "NA ");

	public static MoodyRating find(String value) {
		if (list.contains(value))
			return new MoodyRating(value);
		throw new RuntimeException(format("moody rating {0} not found in {1}", value, list));
	}

	public static int indexOf(String value) {
		return list.indexOf(value);
	}

	public static int compare(MoodyRating aRating, MoodyRating anOtherRating) {
		if (aRating.equals(anOtherRating))
			return 0;
		return min(aRating, anOtherRating) ? 1 : -1;
	}

	public static boolean minOrEqual(MoodyRating threshold, MoodyRating other) {
		return min(threshold, other) || equals(threshold, other);
	}

	private static boolean min(MoodyRating threshold, MoodyRating other) {
		return indexOf(threshold.value) > indexOf(other.value);
	}

	private static boolean equals(MoodyRating threshold, MoodyRating other) {
		return indexOf(threshold.value) == indexOf(other.value);
	}
}
