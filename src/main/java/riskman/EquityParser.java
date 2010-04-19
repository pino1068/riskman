package riskman;

import riskman.instrument.*;
import riskman.location.*;
import riskman.repository.*;

public class EquityParser {
	
	private static final int NAME = 2;
	private static final int ID = 0;
	protected String[] fields;
	private EquityAreaRepository areaRepository = new EquityAreaRepository();

	protected void fields(String string) {
		fields = string.split(";");
	}

	public Equity parse(String line) {
		fields(line);
		Equity equity = new Equity(name());
		parseIdentity(equity);
		parseArea(equity);
		return equity;
	}

	private void parseArea(Equity equity) {
		Location.from(equity).locateIn(areaRepository.locationOf(equity));
	}

	private void parseIdentity(Equity equity) {
		Identities.from(equity).identifiedBy(id());
	}

	private String id() {
		return f(ID);
	}

	private String name() {
		return f(NAME);
	}

	private String f(int fieldPosition) {
		return fields[fieldPosition];
	}
}
