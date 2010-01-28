package ch.siagile.finance.matcher.builder;

import org.hamcrest.*;

import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class FreeTextBuilder implements MatcherBuilder {

	public Matcher<Position> build(String definition) {
		return new IsTextMatcher<Position>(definition.split(","));
	}

	public boolean canBuild(String definition) {
		return true;
	}

	public String cleanUp(String result) {
		return result;
	}

}
