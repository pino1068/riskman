package riskman.repository;

import java.util.*;

import riskman.*;
import riskman.location.*;
import riskman.parser.*;

public class AreaRepository {

	private static Areas areas = new Areas() {
		private static final long serialVersionUID = 1L;
		{
			List<String> lines = new TextRepository()
					.load(getClass().getClassLoader().getResourceAsStream("areas.csv"));
			for (String line : lines)
				add(new AreaParser(line).parse());
		}
	};

	public Area loadById(String id) {
		for (Area area : areas)
			if (checkId(area, id))
				return area;
		return Area.from("UE");
	}

	private static Identity identity(Object object) {
		return Identities.from(object);
	}


	private boolean checkId(Object object, String id) {
		return identity(object).isIdentifiedBy(id);
	}
	
	public Areas all() {
		return areas;
	}
}
