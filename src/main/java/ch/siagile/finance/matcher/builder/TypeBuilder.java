package ch.siagile.finance.matcher.builder;

import org.hamcrest.*;

import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class TypeBuilder implements MatcherBuilder {

	private final String type;

	public TypeBuilder(String type) {
		this.type = type;
	}

	public Matcher<Position> build(String definition) {
		return new IsTypeMatcher<Position>(definition);
	}

	public boolean canBuild(String definition) {
		return definition.equals(type);
	}

	public String cleanUp(String result) {
		return result;
	}

}
