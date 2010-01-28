package ch.siagile.finance.matcher;

import static org.hamcrest.Matchers.*;
import static java.text.MessageFormat.*;

import java.security.*;
import java.util.*;

import org.hamcrest.*;

import ch.siagile.finance.matcher.builder.*;
import ch.siagile.finance.position.*;

public class MatchersBuilder {

	private List<MatcherBuilder> builders = new ArrayList<MatcherBuilder>();

	public MatchersBuilder() {
		builders.add(new RatingBuilder<Position>());
		builders.add(new CurrencyBuilder());
		builders.add(new TypeBuilder("bond"));
		builders.add(new TypeBuilder("equity"));
		builders.add(new AreaBuilder());
		builders.add(new OwnerBuilder());
		builders.add(new FreeTextBuilder());
	}

	public Matcher<Position> build(String definition) {
		return anyOfFilters(selections(definition));
	}

	private String[] selections(String definition) {
		return cleanUp(definition).split("\\+");
	}

	private String cleanUp(String definition) {
		String result = definition.replaceAll("[ ]*([,:+])[ ]*", "$1");
		for (MatcherBuilder b : builders) 
			result = b.cleanUp(result);
		return result;
	}

	private Matcher<Position> anyOfFilters(String[] definitions) {
		Filters filters = new Filters();
		for (String definition : definitions)
			filters.add(allOfFilters(definition));
		return anyOf(filters.matchers);
	}

	private Matcher<Position> allOfFilters(String definitions) {
		Filters filters = new Filters();
		for (String definition : definitions.split(":"))
			filters.add(toFilter(definition));
		return allOf(filters.matchers);
	}

	private Matcher<Position> toFilter(String definition) {
		for (MatcherBuilder b : builders) {
			if (b.canBuild(definition))
				return b.build(definition);
		}
		throw new InvalidParameterException(format("wrong definition: {0}",definition));
	}
}
