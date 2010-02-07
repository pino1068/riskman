package riskman.matcher.builder;

import org.hamcrest.*;

import riskman.location.*;
import riskman.matcher.*;
import riskman.position.*;

public class AreaBuilder implements MatcherBuilder {

	public Matcher<Position> build(String definition) {
		return new IsLocatedMatcher<Position>(definition.split(","));
	}

	public boolean canBuild(String definition) {
		return Areas.all().contains(definition.split(",")[0]);
	}

	public String cleanUp(String result) {
		return result;
	}

}
