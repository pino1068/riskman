package ch.siagile.finance;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.location.*;
import ch.siagile.finance.money.*;
import ch.siagile.finance.position.*;

public class EquityPositionParser extends BaseInstrumentParser {

	private static final int INSTRUMENTID = 16;
	private static final int PRICE = 7;

	protected Position parsePosition(String string) {
		return new EquityPosition(equity(), quantity(), price()).ownedBy(owner());
	}

	private int quantity() {
		return fInt(3);
	}
	
	private Money price() {
		return fMoney(PRICE, CURRENCY);
	}

	private Equity equity() {
		Equity equity = Equities.loadById(f(INSTRUMENTID));
		Location.from(equity).locateIn(Area.from("UE"));
		return equity;
	}

	@Override
	protected String discriminator() {
		return "A";
	}

}
