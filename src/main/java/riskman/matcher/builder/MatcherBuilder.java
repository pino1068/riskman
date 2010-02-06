package riskman.matcher.builder;

import org.hamcrest.*;

import riskman.position.*;

public interface MatcherBuilder {

	Matcher<Position> build(String definition);

	boolean canBuild(String definition);

	String cleanUp(String result);

}
