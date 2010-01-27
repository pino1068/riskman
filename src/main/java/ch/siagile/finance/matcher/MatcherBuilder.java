package ch.siagile.finance.matcher;

import static java.text.MessageFormat.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

import org.hamcrest.*;

import ch.siagile.finance.location.*;
import ch.siagile.finance.position.*;

public class MatcherBuilder {

	public Matcher<Position> build(String definition) {
		String[] orSelections = cleanUp(definition).split(" ");
		return anyOfFilters(orSelections);
	}

	private String cleanUp(String definition) {
		return trim(trim(definition, ","), ":");
	}

	private String trim(String definition, String word) {
		return definition.replaceAll(format("\\s{0}\\s",word), word);
	}

	private Matcher<Position> anyOfFilters(String[] definitions) {
		Filters filters = new Filters();
		for (String definition : definitions) {
			if(isEmpty(definition)) continue;
			filters.add(allOfFilters(definition));
		}
		return anyOf(filters.matchers);
	}

	private boolean isEmpty(String definition) {
		return "".equals(definition.trim());
	}

	private Matcher<Position> allOfFilters(String definitions) {
		Filters filters = new Filters();
		for (String definition : definitions.split(":")) {
			filters.add(toFilter(definition));
		}
		return allOf(filters.matchers);
	}

	private Matcher<Position> toFilter(String definition) {
		if(isRating(definition))
			return RatingMatchers.build(definition.split("=")[1]);
		if (isCurrency(definition))
			return new IsCurrenciesMatcher<Position>(definition.split(","));
		if (isEquitySpecific(definition)) 
			return new IsSpecificEquityMatcher<Position>(definition.split("#")[1]);
		if (isBondType(definition))
			return new IsTypeMatcher<Position>("bond");
		if (isEquityType(definition))
			return new IsTypeMatcher<Position>("equity");
		if (isArea(definition)) 
			return new IsLocatedMatcher<Position>(definition.split(","));
		if (isOwner(definition)) 
			return new IsOwnerMatcher<Position>(definition.split("#")[1].split(","));
		return new IsTextMatcher<Position>(definition.split(","));
	}

	private boolean isOwner(String definition) {
		return definition.startsWith("owner#");
	}

	private boolean isRating(String definition) {
		return definition.startsWith("rating=");
	}

	private boolean isArea(String type) {
		return Areas.defaultAreas().contains(type.split(",")[0]);
	}

	private boolean isEquityType(String type) {
		return type.equals("equity");
	}

	private boolean isBondType(String type) {
		return type.equals("bond");
	}

	private boolean isEquitySpecific(String type) {
		return type.equals("equity#");
	}

	private boolean isCurrency(String type) {
		try {
			Currency.getInstance(type.split(",")[0]);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
