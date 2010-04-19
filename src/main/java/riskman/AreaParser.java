package riskman;

import riskman.location.*;

public class AreaParser extends CsvLineParser {
	
	private static final int NAME = 1;
	private static final int ID = 0;

	public AreaParser(String line) {
		super(line);
	}

	public Area parse() {
		Area area = Area.from(name());
		parseIdentity(area);
		return area;
	}

	private void parseIdentity(Area area) {
		Identities.from(area).identifiedBy(id());
	}

	private String id() {
		return f(ID);
	}

	private String name() {
		return f(NAME);
	}

}