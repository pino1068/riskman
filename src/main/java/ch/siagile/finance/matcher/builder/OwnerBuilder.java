package ch.siagile.finance.matcher.builder;

import org.hamcrest.*;

import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class OwnerBuilder implements MatcherBuilder {

	public Matcher<Position> build(String definition) {
		final String values = definition.split("#")[1];
		return new IsOwnerMatcher<Position>(values.split(","));
	}

	public boolean canBuild(String definition) {
		return definition.startsWith("owner#");
	}

	public String cleanUp(String definition) {
		return definition.replaceAll("owner:", "owner#");
	}

}
