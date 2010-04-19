package riskman.repository;

import java.util.*;

import riskman.*;
import riskman.location.*;

public class AreaRepository {

	private static Areas areas = new Areas() {
		private static final long serialVersionUID = 1L;
		{
			List<String> lines = new TextRepository()
					.load(getClass().getClassLoader().getResourceAsStream("areas.csv"));
			for (String line : lines)
				add(area(line));
		}
	};

	public Area loadById(String id) {
		for (Area area : areas)
			if (checkId(area, id))
				return area;
		return Area.from("UE");
	}

	private boolean checkId(Object object, String id) {
		return identity(object).isIdentifiedBy(id);
	}

	private static Identity identity(Object object) {
		return Identities.from(object);
	}

	private static String id(String line) {
		return line.split(";")[0];
	}

	private static Area area(String line) {
		Area area = Area.from(name(line));
		identify(area, id(line));
		return area;
	}

	private static void identify(Object object, String id) {
		identity(object).identifiedBy(id);
	}

	private static String name(String line) {
		return line.split(";")[1];
	}

	public Areas all() {
		return areas;
	}
}
