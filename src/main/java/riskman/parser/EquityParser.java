package riskman.parser;

import riskman.*;
import riskman.instrument.*;
import riskman.location.*;
import riskman.repository.*;

public class EquityParser extends CsvLineParser {
	
	private static final int NAME = 2;
	private static final int ID = 0;
	private static final int AREA = 18;
	
	private EquityAreaRepository areaRepository = new EquityAreaRepository();

	public EquityParser(String line) {
		super(line);
	}

	public Equity parse() {
		Equity equity = new Equity(id(),name());
		parseIdentity(equity);
		parseArea(equity);
		return equity;
	}

	private void parseArea(Equity equity) {
		Area area = area().or(areaRepository.locationOf(equity));
		Location.from(equity).locateIn(area);
	}

	private Area area() {
		return Area.from(f(AREA));
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
}