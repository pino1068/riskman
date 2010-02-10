package riskman.repository;

import java.util.*;

import riskman.*;
import riskman.instrument.*;

public class EquityRepository {

	private static Set<Equity> equities = new HashSet<Equity>() {
		private static final long serialVersionUID = 1L;
		{
			List<String> lines = new TextRepository()
					.load(getClass().getClassLoader().getResourceAsStream("equities.csv"));
			for (String line : lines)
				add(new EquityParser().parse(line));
		}
	};

	public Equity loadById(String id) {
		for (Equity equity : equities)
			if (checkId(equity, id))
				return equity;
		return null;
	}

	private boolean checkId(Object object, String id) {
		return id(object).isIdentifiedBy(id);
	}

	private Identity id(Object equity) {
		return Identities.from(equity);
	}

	public Iterable<Equity> equities() {
		return equities;
	}

}
