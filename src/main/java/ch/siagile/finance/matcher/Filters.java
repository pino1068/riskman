package ch.siagile.finance.matcher;

import java.util.*;

import org.hamcrest.*;

import static org.hamcrest.Matchers.*;
import ch.siagile.finance.position.*;


public class Filters {
	private Set<Matcher<? extends Position>> matchers = new HashSet<Matcher<? extends Position>>();

	public void add(Matcher<Position> filter) {
		matchers.add(filter);
	}

	public Matcher<Position> anyOfThem() {
		return anyOf(matchers);
	}

	public Matcher<Position> allOfThem() {
		return allOf(matchers);
	}
}
