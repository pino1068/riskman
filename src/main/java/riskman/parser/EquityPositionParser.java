package riskman.parser;

import static java.text.MessageFormat.*;
import riskman.instrument.*;
import riskman.location.*;
import riskman.money.*;
import riskman.position.*;

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
		if(equity==null)
			throw new RuntimeException(format("Equity Id <{1}> not found for InstrumentId<{0}>",f(INSTRUMENTID),name()));
		Location.from(equity).locateIn(Area.from("UE"));
		return equity;
	}

	@Override
	protected String discriminator() {
		return "A";
	}

}
