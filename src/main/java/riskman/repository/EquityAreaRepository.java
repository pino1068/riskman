package riskman.repository;

import java.util.*;

import riskman.*;
import riskman.instrument.*;
import riskman.location.*;

public class EquityAreaRepository {

	private static List<String> lines = new TextRepository().load(EquityAreaRepository.class.getClassLoader().getResourceAsStream("aree-equity.csv"));
	private static AreaRepository areaRepository = new AreaRepository();

	public Area locationOf(Equity equity) {
		for (String line : lines) 
			if(setIdentity(equity, equityId(line)))
				return areaById(areaId(line));
		return Area.from("USA");
	}

	private boolean setIdentity(Equity equity, String equityId) {
		return Identities.from(equity).isIdentifiedBy(equityId);
	}

	private Area areaById(String id) {
		return areaRepository.loadById(id);
	}

	private String areaId(String line) {
		return line.split(";")[2];
	}

	private String equityId(String line) {
		return line.split(";")[1];
	}

}
