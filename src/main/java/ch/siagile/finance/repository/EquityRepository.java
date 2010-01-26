package ch.siagile.finance.repository;

import java.util.*;

import ch.siagile.finance.*;
import ch.siagile.finance.instrument.*;

public class EquityRepository {

	private static Set<Equity> equities = new HashSet<Equity>() {
		private static final long serialVersionUID = 1L;
		{
			List<String> lines = new TextRepository()
					.load("src/main/resources/equities.csv");
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

}
