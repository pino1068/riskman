package riskman.instrument;

import riskman.repository.*;

public class Equities {

	public static Equity loadById(String id) {
		return new EquityRepository().loadById(id);
	}

}
