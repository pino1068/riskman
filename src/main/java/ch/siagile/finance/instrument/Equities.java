package ch.siagile.finance.instrument;

import ch.siagile.finance.repository.*;

public class Equities {

	public static Equity loadById(String id) {
		return new EquityRepository().loadById(id);
	}

}
