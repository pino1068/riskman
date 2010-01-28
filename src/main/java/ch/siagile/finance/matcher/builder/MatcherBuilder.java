package ch.siagile.finance.matcher.builder;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public interface MatcherBuilder {

	Matcher<Position> build(String definition);

	boolean canBuild(String definition);

	String cleanUp(String result);

}
