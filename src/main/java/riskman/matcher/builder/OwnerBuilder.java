package riskman.matcher.builder;

import org.hamcrest.*;

import riskman.*;
import riskman.matcher.*;
import riskman.position.*;

public class OwnerBuilder implements MatcherBuilder {

	private OwnerRepository repository = new OwnerRepository();

	public Matcher<Position> build(String definition) {
//		final String values = definition.split("#")[1];
		return new IsOwnerMatcher<Position>(definition.split(","));
	}

	public boolean canBuild(String definition) {
		return repository .contains(definition.split(",")[0]);
//		return definition.startsWith("owner#");
	}

	public String cleanUp(String definition) {
		return definition;//.replaceAll("owner:", "owner#");
	}

}
