package ch.siagile.finance;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.money.*;
import ch.siagile.finance.position.*;

public class EquityParser extends BaseInstrumentParser {

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
		return Equity.from(name());
	}

	@Override
	protected String discriminator() {
		return "A";
	}

}
