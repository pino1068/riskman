package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;

public class PositionTest {
	@Test
	public void shouldCreateAccountPosition() {
		AccountPosition account = new AccountPosition("pippo", CHF(500));

		assertEquals(CHF(500), account.balance());
	}

	@Test
	public void shouldCreateEquityPosition() {
		Equity equity = Equity.from("IBM");
		EquityPosition position = new EquityPosition(equity, 5, CHF(IBM_PRICE));

		assertEquals(CHF(5 * IBM_PRICE), position.balance());
	}

	@Test
	public void shouldCreatePortfolioWith2Positions() {
		AccountPosition account = account("pippo", CHF(500));
		EquityPosition equity = equity("IBM", 5, CHF(IBM_PRICE));

		Positions positions = new Positions(account, equity);

		Money totalAmount = account.balance().plus(equity.balance());
		assertEquals(totalAmount, positions.value());
	}

	@Test
	public void shouldCreateBond() {
		BondPosition bond = bond("Inter American Dev. Bank", CHF(1000));

		AccountPosition account = account("pippo", CHF(500));
		Positions positions = new Positions(bond, account);

		Money totalAmount = account.balance().plus(bond.balance());
		assertEquals(totalAmount, positions.value());
	}

}
