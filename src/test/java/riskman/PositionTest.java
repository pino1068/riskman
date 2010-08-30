package riskman;

import static riskman.fixtures.Fixtures.*;
import static riskman.matcher.IsRatingInMatcher.*;
import static riskman.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.*;

import riskman.instrument.*;
import riskman.instrument.rating.*;
import riskman.location.*;
import riskman.money.*;
import riskman.position.*;

public class PositionTest {
	@Test
	public void shouldCreateAccountPosition() {
		BasePosition account = new AccountPosition("pippo", CHF(500));

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
		Position account = account("pippo", CHF(500));
		Position equity = equity("IBM", 5, CHF(IBM_PRICE));

		Positions positions = new Positions(account, equity);

		Money totalAmount = account.balance().plus(equity.balance());
		assertEquals(totalAmount, positions.value());
	}

	@Test
	public void shouldBondPositionHaveOwnBalance() {
		Position bondPosition = bond(interAmericaDevBankBond(), CHF(5000),
				"102 %");

		assertEquals(CHF(5100), bondPosition.balance());
	}

	private Bond interAmericaDevBankBond() {
		return Bond.from("Inter American Dev. Bank", MoodyRatings.find("Aaa"),
				Area.from("UE"));
	}

	@Test
	public void shouldTreatBondPositionLikePosition() {
		Position bondPosition = bond(interAmericaDevBankBond(), CHF(5000),
				"102 %");

		Position accountPosition = account("pippo", CHF(500));
		Positions positions = new Positions(bondPosition, accountPosition);

		assertEquals(CHF(5600), positions.value());
	}

	@Test
	public void shouldBondPositionHaveRating() {
		BondPosition bondPosition = (BondPosition) bond(
				interAmericaDevBankBond(), CHF(5000), "102 %");

		assertEquals(bondPosition.rating(), MoodyRatings.find("Aaa"));
	}

	@Test
	public void shouldMatchBondPositingInRatings() {
		Position bondPosition = bond(interAmericaDevBankBond(), CHF(5000),
				"102 %");

		MoodyRating C = MoodyRatings.find("C");
		MoodyRating AAA = MoodyRatings.find("Aaa");

		assertThat(bondPosition, is(ratingIn(C, AAA)));
	}

	@Test
	public void shouldSortPositionsByName() {
		Position bPos = bond(interAmericaDevBankBond(), CHF(50), "10 %");
		Position aPos = account("pippo", CHF(500));
		Positions pre_sort = new Positions(bPos, aPos);
		final Iterator<Position> pre_iter = pre_sort.iterator();
		assertThat(pre_iter.next().toString()
				.compareTo(pre_iter.next().toString()), is(greaterThan(0)));

		Positions post_sort = pre_sort.sortByName();

		final Iterator<Position> post_iter = post_sort.iterator();
		assertThat(post_iter.next().toString()
				.compareTo(post_iter.next().toString()), is(lessThan(0)));
	}
}
