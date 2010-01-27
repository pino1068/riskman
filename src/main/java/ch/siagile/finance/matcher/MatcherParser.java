package ch.siagile.finance.matcher;

import static java.text.MessageFormat.*;
import static org.hamcrest.Matchers.*;

import java.security.*;
import java.util.*;

import org.hamcrest.*;

import ch.siagile.finance.location.*;
import ch.siagile.finance.position.*;

public class MatcherParser {

	public Matcher<Position> parse(String definition) {
		String[] orSelections = cleanUp(definition).split(" ");
		try {
			return anyOfFilters(orSelections);
		} catch (RuntimeException e) {
			throw new RuntimeException(format("wrong definition <{0}>", definition), e);
		}
	}

	private String cleanUp(String definition) {
		return trim(trim(definition, ","), ":");
	}

	private String trim(String definition, String word) {
		return definition.replaceAll(format("\\s{0}\\s",word), word);
	}

	private Matcher<Position> anyOfFilters(String[] definitions) {
		Filters filters = new Filters();
		for (String definition : definitions) 
			try {
				filters.add(allOfFilters(definition));
			} catch (RuntimeException e) {
				throw new RuntimeException(format("wrong definition <{0}>",definition), e);
			}
		return anyOf(filters.matchers);
	}

	private Matcher<Position> allOfFilters(String definitions) {
		Filters filters = new Filters();
		for (String definition : definitions.split(":")) {
			filters.add(toFilter(definition));
		}
		return allOf(filters.matchers);
	}

	private Matcher<Position> toFilter(String definition) {
		if (isCurrency(definition))
			return new IsCurrenciesMatcher<Position>(definition.split(","));
		if (isEquitySpecific(definition)) 
			return new IsSpecificEquityMatcher<Position>(definition.split("#")[1]);
		if (isBondType(definition))
			return new IsBondMatcher<Position>();
		if (isEquityType(definition))
			return new IsEquityMatcher<Position>();
		if (isArea(definition)) 
			return new IsLocatedMatcher<Position>(definition.split(","));
		throw new InvalidParameterException(format("invalid definition part <{0}>",definition));
	}

	private boolean isArea(String type) {
		return Areas.defaultAreas().contains(type.split(",")[0]);
	}

	private boolean isEquityType(String type) {
		return type.contains("equity");
	}

	private boolean isBondType(String type) {
		return type.contains("bond");
	}

	private boolean isEquitySpecific(String type) {
		return type.contains("equity#");
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
