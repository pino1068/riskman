package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static ch.siagile.finance.money.Money.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.constraint.*;
import ch.siagile.finance.instrument.*;
import ch.siagile.finance.instrument.rating.*;
import ch.siagile.finance.location.*;
import ch.siagile.finance.position.*;

public class ConstraintTest {

	private Constraint constraint;
	private Positions positions;

	@Test
	public void shouldEquityConstraintMaxMatchOnPortofolioWithLessEquity() {
		constraint = new EquityConstraint("max: 50%");
		positions = new Positions(account("pluto", CHF(2000)), IBM(CHF(100)));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquityConstraintMaxMatchOnPortofolioWithMoreEquity() {
		constraint = new EquityConstraint("max: 50%");
		positions = new Positions(account("pluto", CHF(999.0)), IBM(CHF(1000)));

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquityConstraintMinMatchOnPortofolioWithLessEquity() {
		constraint = new EquityConstraint("min:50%");
		positions = new Positions(account("pluto", CHF(2000)), IBM(CHF(100)));

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquityConstraintMinMatchOnPortofolioWithMoreEquity() {
		constraint = new EquityConstraint("min:50%");
		positions = new Positions(account("pluto", CHF(1000)), IBM(CHF(1000)));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMaxMatchOnPortfolio() {
		constraint = new EquityConstraint("max:3%", "UBS");
		positions = new Positions(account("pluto", CHF(1000)), UBS(CHF(10)), UBS(CHF(10)), IBM(CHF(1000)));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMaxMatchOnPortfolioWithTooManyUBS() {
		constraint = new EquityConstraint("max:5%", "UBS");
		Position IBM1000chf = equity("IBM", 10, CHF(100));
		Position UBS2000chf = equity("UBS", 200, CHF(10));
		positions = new Positions(UBS2000chf, IBM1000chf);

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMinMatchOnPortfolio() {
		constraint = new EquityConstraint("min:1%", "UBS");
		positions = new Positions(account("pluto", CHF(1)), UBS(CHF(20)), IBM(CHF(1000)));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMinDoesntMatchOnPortfolio() {
		constraint = new EquityConstraint("min:1%", "UBS");
		positions = new Positions(account("pluto", CHF(1)), UBS(CHF(10)), IBM(CHF(10000)));

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldEquitySpecificConstraintMinMatchAListOfEquities() {
		constraint = new EquityConstraint("min:50%", "UBS", "IBM");
		positions = new Positions(account("pluto", CHF(20)), UBS(CHF(40)), IBM(CHF(40)));

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldCheckOnlyValidAreaOnPositions() {
		final MoodyRating A2 = MoodyRatings.find("A2");
		final Area UE = Area.from("UE");

		positions = new Positions() {
			{
				add(account("pluto", CHF(10)));
				add(UBS(CHF(100)));
				add(bond(Bond.from("GECC", A2, UE), CHF(100), "100%"));
			}
		};
		Constraint constraint = new AreaConstraint("min:20%", "UE", "USA");

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldCheckSomeBondInRatingRange() {

		final MoodyRating A2 = MoodyRatings.find("A2");
		final Area UE = Area.from("UE");

		positions = new Positions() {
			{
				add(account("pluto", CHF(10)));
				add(UBS(CHF(100)));
				add(bond(Bond.from("GECC", A2, UE), CHF(100), "100%"));
			}
		};

		Constraint constraint = new RatingConstraint("range:B1,A2", "min:20%");

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldCheckSomeBondInRatingRangeA1Aaa() {

		final MoodyRating Aaa = MoodyRatings.find("Aaa");
		final Area UE = Area.from("UE");

		positions = new Positions() {
			{
				add(account("pluto", CHF(10)));
				add(UBS(CHF(100)));
				add(bond(Bond.from("GECC", Aaa, UE), CHF(100), "100%"));
			}
		};

		Constraint constraint = new RatingConstraint("range:A1,Aaa", "min:20%");

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldCheckSomeBondInRatingMaxA1() {

		final MoodyRating B2 = MoodyRatings.find("B2");
		final Area UE = Area.from("UE");

		positions = new Positions() {
			{
				add(account("pluto", CHF(10)));
				add(UBS(CHF(100)));
				add(bond(Bond.from("GECC", B2, UE), CHF(100), "100%"));
			}
		};

		Constraint constraint = new RatingConstraint("max:A1", "min:20%");

		assertTrue(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldBondConstraintMin20NotMatch() {
		constraint = new BondConstraint("min: 20%");
		positions = new Positions(account("pluto", CHF(999.0)), IBM(CHF(1000)));

		assertFalse(constraint.checkLimitOn(positions));
	}

	@Test
	public void shouldBondConstraintMin20Match() {
		constraint = new BondConstraint("min: 20%");
		positions = new Positions() {
			{
				add(account("pluto", CHF(10)));
				add(UBS(CHF(100)));
				add(bond(Bond.from("GECC", "UE"), CHF(100), "100%"));
			}
		};

		assertTrue(constraint.checkLimitOn(positions));
	}
}
