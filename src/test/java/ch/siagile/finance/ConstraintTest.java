package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ConstraintTest {

	private Constraint constraint;
	private Positions positions;

	@Test
	public void shouldEquityConstraintMaxMatchOnPortofolioWithLessEquity() {
		constraint = new EquityConstraint("max: 50%");
		positions = new Positions(account("pluto", CHF(2000)), IBM(10));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquityConstraintMaxMatchOnPortofolioWithMoreEquity() {
		constraint = new EquityConstraint("max: 50%");
		positions = new Positions(account("pluto", CHF(999.0)), IBM(10));

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquityConstraintMinMatchOnPortofolioWithLessEquity() {
		constraint = new EquityConstraint("min:50%");
		positions = new Positions(account("pluto", CHF(2000)), IBM(10));

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquityConstraintMinMatchOnPortofolioWithMoreEquity() {
		positions = new Positions(account("pluto", CHF(1000)), IBM(10));
		constraint = new EquityConstraint("min:50%");

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMaxMatchOnPortfolio() {
		constraint = new EquitySpecificConstraint("UBS", "max:3%");
		positions = new Positions(account("pluto", CHF(1000)), UBS(1), UBS(1), IBM(10));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMaxMatchOnPortfolioWithTooManyUBS() {
		constraint = new EquitySpecificConstraint("UBS", "max:5%");
		EquityPosition IBM1000chf = equity("IBM", 10, CHF(100));
		EquityPosition UBS2000chf = equity("UBS", 200, CHF(10));
		positions = new Positions(UBS2000chf, IBM1000chf);

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMinMatchOnPortfolio() {
		constraint = new EquitySpecificConstraint("UBS", "min:1%");
		positions = new Positions(account("pluto", CHF(1)), UBS(2), IBM(10));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMinDoesntMatchOnPortfolio() {
		constraint = new EquitySpecificConstraint("UBS", "min:1%");
		positions = new Positions(account("pluto", CHF(1)), UBS(1), IBM(10));

		assertFalse(constraint.checkLimitOn(positions));
	}

	// @Test
	// public void shouldUSDInCHFMax30PercentOfPortfolio() {
	// Portfolio positions = new Portfolio();
	// positions.add( account("pluto", CHF(1)));
	// positions.add( account("dollar account", USD(CHF(290))));
	// positions.add( UBS(1));
	// positions.add( IBM(10));
	// Constraint constraint = new CurrencyConstraint("USD","min", 0.01);
	//		
	// assertFalse(constraint.checkLimitOn(positions));
	// }

}
