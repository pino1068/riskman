package riskman.matcher;

import java.util.*;

import org.hamcrest.*;

import riskman.matcher.builder.*;
import riskman.position.*;

public class CurrencyBuilder implements MatcherBuilder {

	public boolean canBuild(String definition) {
		try {
			Currency.getInstance(definition.split(",")[0]);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public Matcher<Position> build(String definition) {
		return new IsCurrenciesMatcher<Position>(definition.split(","));
	}

	public String cleanUp(String result) {
		return result;
	}

}
