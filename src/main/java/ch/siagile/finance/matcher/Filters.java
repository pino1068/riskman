package ch.siagile.finance.matcher;

import java.util.*;

import org.hamcrest.*;

import ch.siagile.finance.position.*;

public class Filters {
	Set<Matcher<? extends Position>> matchers = new HashSet<Matcher<? extends Position>>();

	public void add(Matcher<Position> filter) {
		matchers.add(filter);
	}
}
