package ch.siagile.finance;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.app.*;
import ch.siagile.finance.instrument.*;
import ch.siagile.finance.location.*;
import ch.siagile.finance.position.*;

public class ShellTest {
	private String output;
	private Shell command;
	private Positions positions;

	@Before
	public void setUp() {
		output = "";
		command = new Shell();
		positions = new Positions(account("name", CHF(30)), account("name2", USD(70)));
	}

	@Test
	public void shouldChfMax20PercentKO() {
		output = command.execute(positions, "CHF max:20%");
		assertThat(output, containsString("CHF max:20% KO"));
	}

	@Test
	public void shouldEquityMin20PercentKO() {
		output = command.execute(positions, "equity min:20%");
		assertThat(output, containsString("equity min:20% KO"));
	}

	@Test
	public void shouldAreaInUEMin20PercentKO() {
		output = command.execute(positions, "area:UE min:20%");
		assertThat(output, containsString("area:UE min:20% KO"));
	}


	@Test
	public void shouldAreaInUSAMin20PercentKO() {
		positions = new Positions(bond(Bond.from("name", "USA"), CHF(100), "100%"));
		output = command.execute(positions, "area:USA min:20%");
		assertThat(output, containsString("area:USA min:20% OK"));
	}

	@Test
	public void shouldAreaInUSAorUEMin20PercentKO() {
		positions = new Positions(bond(Bond.from("name", "USA"), CHF(50), "100%"), bond(Bond.from("name", "UE"), CHF(50), "100%"));
		output = command.execute(positions, "area:USA,UE min:100%");
		assertThat(output, containsString("area:USA,UE min:100% OK"));
	}

	@Test
	public void shouldEquityMin20PercentOK() {
		positions = new Positions(equity("IBM", 1, CHF(30)), account("name2", USD(70)));
		output = command.execute(positions, "equity min:20%");
		assertThat(output, containsString("equity min:20% OK"));
	}

	@Test
	public void shouldEquityIBMMin40PercentKO() {
		positions = new Positions(equity("IBM", 1, CHF(30)), equity("ORCL", 1, CHF(70)));
		output = command.execute(positions, "equity:IBM min:40%");
		assertThat(output, containsString("equity:IBM min:40% KO"));
	}

	@Test
	public void shouldEquityIBMMin20PercentOK() {
		positions = new Positions(equity("IBM", 1, CHF(30)), equity("ORCL", 1, CHF(70)));
		output = command.execute(positions, "equity:ORCL min:40%");
		assertThat(output, containsString("equity:ORCL min:40% OK"));
	}

	@Test
	public void shouldEquityORCLandIBMMin80PercentOK() {
		positions = new Positions(equity("IBM", 1, CHF(30)), equity("ORCL", 1, CHF(70)));
		output = command.execute(positions, "equity:IBM,ORCL min:80%");
		assertThat(output, containsString("equity:IBM,ORCL min:80% OK"));
	}

	@Test
	public void shouldThrowExceptionWhenCommandIsNotRecognized() {
		try {
			command.execute(positions, "wrongCommand min:20%");
			fail("expected exception: wrongCommand min:20% not recognized");
		} catch (Exception expectedBehaivor) {
		}
	}

	@Test
	public void shouldChfMax40PercentOK() {
		output = command.execute(positions, "CHF max:40%");
		assertThat(output, containsString("CHF max:40% OK"));
	}

	@Test
	public void shouldChfMin20PercentOK() {
		output = command.execute(positions, "CHF min:20%");
		assertThat(output, containsString("CHF min:20% OK"));
	}

	@Test
	public void shouldChfOrUsdMin20PercentOK() {
		output = command.execute(positions, "CHF,USD min:80%");
		assertThat(output, containsString("CHF,USD min:80% OK"));
	}

	@Test
	public void shouldChfMin40PercentKO() {
		output = command.execute(positions, "CHF min:40%");
		assertThat(output, containsString("CHF min:40% KO"));
	}

	@Test
	public void shouldUsdMin40PercentKO() {
		output = command.execute(positions, "USD min:80%");
		assertThat(output, containsString("USD min:80% KO"));
	}

	@Test
	public void shouldUsdRangePercentKO() {
		output = command.execute(positions, "USD range:60%,80%");
		assertThat(output, containsString("USD range:60%,80% OK"));
	}

	@Test
	public void shouldUsdNotInRangePercentKO() {
		output = command.execute(positions, "CHF range:60%,80%");
		assertThat(output, containsString("CHF range:60%,80% KO"));
	}

	@Test
	public void shouldMaxRatingPercentKO() {
		output = command.execute(positions, "rating:range:B1,A2 min:60%");
		assertThat(output, containsString("rating:range:B1,A2 min:60% KO"));
	}

	@Test
	public void shouldMaxRatingPercentOK() {
		final MoodyRating A2 = MoodyRatings.find("A2");
		final Area UE = Area.from("UE");

		positions = new Positions() {
			{
				add(account("pluto", CHF(10)));
				add(UBS(CHF(10)));
				add(bond(Bond.from("GECC", A2, UE), CHF(100), "100%"));
			}
		};
		output = command.execute(positions, "rating:range:B1,A2 min:60%");
		assertThat(output, containsString("rating:range:B1,A2 min:60% OK"));
	}

	@Test
	public void shouldOK() {
		output = command.execute(positions, "");
		assertThat(output, containsString("OK"));
	}
}