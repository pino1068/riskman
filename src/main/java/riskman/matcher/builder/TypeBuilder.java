package riskman.matcher.builder;

import org.hamcrest.*;

import riskman.matcher.*;
import riskman.position.*;

public class TypeBuilder implements MatcherBuilder {

	private final String type;

	public TypeBuilder(String type) {
		this.type = type;
	}

	public Matcher<Position> build(String definition) {
		return new IsTypeMatcher<Position>(definition.split(","));
	}

	public boolean canBuild(String definition) {
		for (String part : definition.split(","))
			if (part.equals(type))
				return true;
		return false;
	}

	public String cleanUp(String result) {
		return result;
	}

}
