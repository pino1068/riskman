package ch.siagile.finance.app;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static ch.siagile.finance.money.Money.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.instrument.rating.*;
import ch.siagile.finance.location.*;
import ch.siagile.finance.position.*;

public class ShellTest {

	private static final MoodyRating A2 = MoodyRatings.find("A2");
	private static final Area UE = Area.from("UE");
	private String output;
	private Shell command;
	private Positions positions;

	@Before
	public void setUp() {
		output = "";
		command = new Shell();
		positions = new Positions();
	}
	
	@Test 
	public void shouldSelectPositionsOnly() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("CHF max:20%");
		execute();
		assertThat(output, containsString("CHF max:20% KO"));
		assertThat(output, containsString("but is 28.037% 30 CHF of 107 CHF"));
	}

	@Test
	public void shouldChfMax20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("CHF max:20%");
		execute();
		assertThat(output, containsString("CHF max:20% KO"));
		assertThat(output, containsString("but is 28.037% 30 CHF of 107 CHF"));
	}

	@Test
	public void shouldEquityMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("equity min:20%");
		execute();
		assertThat(output, containsString("equity min:20% KO"));
	}

	@Test @Ignore
	public void shouldEquityAndBondMin20PercentOK() {
		def(account("name", CHF(30)));
		def(equity("IBM", 1, CHF(30)));
		def(bond(Bond.from("name", "USA"), CHF(40), "100%"));
		def("equity,bond min:20%");
		execute();
		assertThat(output, containsString("equity,bond min:20% OK"));
	}

	@Test
	@Ignore
	public void shouldEquityAndBondMax20PercentKO() {
		def(account("name", CHF(30)));
		def(equity("IBM", 1, CHF(30)));
		def(bond(Bond.from("name", "USA"), CHF(40), "100%"));
		def("equity,bond max:69%");
		execute();
		System.out.println(output);
		assertThat(output, containsString("equity,bond max:69% KO"));
	}

	@Test
	public void shouldBondMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("bond min:20%");
		execute();
		assertThat(output, containsString("bond min:20% KO"));
	}

	@Test
	public void shouldBondMin20PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", CHF(70)));
		def(bond(Bond.from("name", "USA"), CHF(100), "100%"));
		def("bond min:20%");
		execute();
		assertThat(output, containsString("bond min:20% OK"));
	}

	@Test
	public void shouldAreaInUEMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("area:UE min:20%");
		execute();
		assertThat(output, containsString("area:UE min:20% KO"));
	}

	@Test
	public void shouldAreaInUSAMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def(bond(Bond.from("name", "USA"), CHF(100), "100%"));
		def("area:USA min:20%");
		execute();
		assertThat(output, containsString("area:USA min:20% OK"));
	}

	@Test
	public void shouldAreaInUSAorUEMin20PercentKO() {
		def(bond(Bond.from("name", "USA"), CHF(50), "100%"));
		def(bond(Bond.from("name", "UE"), CHF(50), "100%"));
		def("area:USA,UE min:100%");
		execute();
		assertThat(output, containsString("area:USA,UE min:100% OK"));
	}

	@Test
	public void shouldEquityMin20PercentOK() {
		def(equity("IBM", 1, CHF(30)));
		def(account("name2", USD(70)));
		def("equity min:20%");
		execute();
		assertThat(output, containsString("equity min:20% OK"));
	}

	@Test
	public void shouldEquityIBMMin40PercentKO() {
		def(equity("IBM", 1, CHF(30)));
		def(equity("ORCL", 1, CHF(70)));
		def("equity:IBM min:40%");
		execute();
		assertThat(output, containsString("equity:IBM min:40% KO"));
	}

	@Test
	public void shouldEquityIBMMin40PercentOK() {
		def(equity("IBM", 1, CHF(30)));
		def(equity("ORCL", 1, CHF(70)));
		def("equity:ORCL min:40%");
		execute();
		assertThat(output, containsString("equity:ORCL min:40% OK"));
	}

	@Test
	public void shouldEquityORCLandIBMMin80PercentOK() {
		def(equity("IBM", 1, CHF(30)));
		def(equity("ORCL", 1, CHF(70)));
		def("equity:IBM,ORCL min:80%");
		execute();
		assertThat(output, containsString("equity:IBM,ORCL min:80% OK"));
	}

	@Test
	public void shouldThrowExceptionWhenCommandIsNotRecognized() {
		def("wrongCommand min:20%");
		execute();
		assertThat(output, containsString("error:wrongCommand min:20%"));
	}

	@Test
	public void shouldChfMax40PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("CHF max:40%");
		execute();
		assertThat(output, containsString("CHF max:40% OK"));
	}

	@Test
	public void shouldChfMin20PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("CHF min:20%");
		execute();
		assertThat(output, containsString("CHF min:20% OK"));
	}

	@Test
	public void shouldChfOrUsdMin20PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("CHF,USD min:80%");
		execute();
		assertThat(output, containsString("CHF,USD min:80% OK"));
	}

	@Test
	public void shouldChfMin40PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("CHF min:40%");
		execute();
		assertThat(output, containsString("CHF min:40% KO"));
	}

	@Test
	public void shouldUsdMin40PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("USD min:80%");
		execute();
		assertThat(output, containsString("USD min:80% KO"));
	}

	@Test
	public void shouldUsdRangePercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("USD range:60%,80%");
		execute();
		assertThat(output, containsString("USD range:60%,80% OK"));
	}

	@Test
	public void shouldUsdNotInRangePercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("CHF range:60%,80%");
		execute();
		assertThat(output, containsString("CHF range:60%,80% KO"));
	}

	@Test
	public void shouldMaxRatingPercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("rating:range:B1,A2 min:60%");
		execute();
		assertThat(output, containsString("rating:range:B1,A2 min:60% KO"));
	}

	@Test
	public void shouldLoadPortfolioCsv() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def("load:src/test/resources/portfolio1.csv");
		execute();
		assertThat(output, containsString("load:src/test/resources/portfolio1.csv OK"));
	}

	@Test
	public void shouldMaxRatingPercentOK() {
		def(account("pluto", CHF(10)));
		def(UBS(CHF(10)));
		def(bond(Bond.from("GECC", A2, UE), CHF(100), "100%"));
		def("rating:range:B1,A2 min:60%");
		execute();
		assertThat(output, containsString("rating:range:B1,A2 min:60% OK"));
	}

	@Test
	public void shouldOK() {
		def("");
		execute();
		assertThat(output, containsString("OK"));
	}

	private void def(Position position) {
		positions.add(position);
	}

	private List<String> definitions = new LinkedList<String>();

	private void def(String definition) {
		definitions.add(definition);
	}

	private void execute() {
		output = command.execute(positions, definitions);
	}
}