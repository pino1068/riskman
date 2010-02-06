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
	private Shell shell;
	private Positions positions;

	@Before
	public void setUp() {
		output = "";
		shell = new Shell();
		positions = new Positions();
	}
	
	@Test  
	public void shouldSelectPositionsOnly() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("CHF max:20%");
		assertThat(output, containsString("CHF max:20% KO"));
		assertThat(output, containsString("but is 28.037%: 30 CHF of 107 CHF"));
	}

	@Test 
	public void shouldChfMax20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("CHF max:20%");
		assertThat(output, containsString("CHF max:20% KO"));
		assertThat(output, containsString("but is 28.037%: 30 CHF of 107 CHF"));
	}

	@Test 
	public void shouldEquityMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("equity min:20%");
		assertThat(output, containsString("equity min:20% KO"));
	}

	@Test  
	public void shouldEquityAndBondMin20PercentOK() {
		def(account("name", CHF(30)));
		def(equity("IBM", 1, CHF(30)));
		def(bond(Bond.from("name", "USA"), CHF(40), "100%"));
		execute("equity,bond min:20%");
		assertThat(output, containsString("equity,bond min:20% OK"));
	}

	@Test 
	
	public void shouldEquityAndBondMax20PercentKO() {
		def(account("name", CHF(30)));
		def(equity("IBM", 1, CHF(30)));
		def(bond(Bond.from("name", "USA"), CHF(40), "100%"));
		execute("equity,bond max:69%");
		assertThat(output, containsString("equity,bond max:69% KO"));
	}

	@Test 
	public void shouldBondMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("bond min:20%");
		assertThat(output, containsString("bond min:20% KO"));
	}

	@Test 
	public void shouldBondMin20PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", CHF(70)));
		def(bond(Bond.from("name", "USA"), CHF(100), "100%"));
		execute("bond min:20%");
		assertThat(output, containsString("bond min:20% OK"));
	}

	@Test 
	public void shouldAreaInUEMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("UE min:20%");
		assertThat(output, containsString("UE min:20% KO"));
	}

	@Test 
	public void shouldAreaInUSAMin20PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		def(bond(Bond.from("name", "USA"), CHF(100), "100%"));
		execute("USA min:20%");
		assertThat(output, containsString("USA min:20% OK"));
	}

	@Test 
	public void shouldAreaInUSAorUEMin20PercentKO() {
		def(bond(Bond.from("name", "USA"), CHF(50), "100%"));
		def(bond(Bond.from("name", "UE"), CHF(50), "100%"));
		execute("USA,UE min:100%");
		assertThat(output, containsString("USA,UE min:100% OK"));
	}

	@Test 
	public void shouldEquityMin20PercentOK() {
		def(equity("IBM", 1, CHF(30)));
		def(account("name2", USD(70)));
		execute("equity min:20%");
		assertThat(output, containsString("equity min:20% OK"));
	}

	@Test 
	public void shouldEquityIBMMin40PercentKO() {
		def(equity("IBM", 1, CHF(30)));
		def(equity("ORCL", 1, CHF(70)));
		execute("IBM min:40%");
		assertThat(output, containsString("IBM min:40% KO"));
	}

	@Test 
	public void shouldEquityIBMMin40PercentOK() {
		def(equity("IBM", 1, CHF(30)));
		def(equity("ORCL", 1, CHF(70)));
		execute("ORCL min:40%");
		assertThat(output, containsString("ORCL min:40% OK"));
	}

	@Test 
	public void shouldEquityORCLandIBMMin80PercentOK() {
		def(equity("IBM", 1, CHF(30)));
		def(equity("ORCL", 1, CHF(70)));
		execute("IBM,ORCL min:80%");
		assertThat(output, containsString("IBM,ORCL min:80% OK"));
	}

	@Test 
	public void shouldThrowExceptionWhenCommandIsNotRecognized() {
		def(account("name", CHF(30)));
		execute("wrongCommand min:20%");
		assertThat(output, containsString("wrongCommand min:20% KO but is 0%: 0 CHF of 30 CHF"));
	}

	@Test 
	public void shouldChfMax40PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("CHF max:40%");
		assertThat(output, containsString("CHF max:40% OK"));
	}

	@Test 
	public void shouldChfMin20PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("CHF min:20%");
		assertThat(output, containsString("CHF min:20% OK"));
	}

	@Test 
	public void shouldChfOrUsdMin20PercentOK() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("CHF,USD min:80%");
		assertThat(output, containsString("CHF,USD min:80% OK"));
	}

	@Test 
	public void shouldChfMin40PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("CHF min:40%");
		assertThat(output, containsString("CHF min:40% KO"));
	}

	@Test 
	public void shouldUsdMin40PercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("USD min:80%");
		assertThat(output, containsString("USD min:80% KO"));
	}

	@Test 
	public void shouldUsdRangePercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("USD range:60%,80%");
		assertThat(output, containsString("USD range:60%,80% OK"));
	}

	@Test 
	public void shouldUsdNotInRangePercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("CHF range:60%,80%");
		assertThat(output, containsString("CHF range:60%,80% KO"));
	}

	@Test 
	public void shouldMaxRatingPercentKO() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("range:B1,A2 min:60%");
		assertThat(output, containsString("range:B1,A2 min:60% KO"));
	}

	@Test 
	public void shouldLoadPortfolioCsv() {
		def(account("name", CHF(30)));
		def(account("name2", USD(70)));
		execute("load:src/test/resources/portfolio1.csv");
//		assertThat(output, containsString("load:src/test/resources/portfolio1.csv OK"));
		assertThat(output, containsString("OK"));
	}

	@Test 
	public void shouldMaxRatingPercentOK() {
		def(account("pluto", CHF(10)));
		def(UBS(CHF(10)));
		def(bond(Bond.from("GECC", A2, UE), CHF(100), "100%"));
		execute("range:B1,A2 min:60%");
		execute();
		assertThat(output, containsString("range:B1,A2 min:60% OK"));
	}

	@Test 
	public void shouldCheckMaxOnSplitByOwner() {
		def(account("pluto", CHF(10)));
		def(UBS(CHF(10)));
		def(bond(Bond.from("GECC", A2, UE), CHF(100), "100%"));
		execute("check:owner max:10%");
		assertThat(output, containsString("bondOwner KO, check <max:10%> but is 83.333% (100 CHF over 120 CHF)"));
		assertThat(output, containsString("accountOwner OK"));
		assertThat(output, containsString("equityOwner OK"));
	}

	@Test 
	public void shouldOK() {
		execute("");
		assertThat(output, containsString("OK"));
	}

	private void def(Position position) {
		positions.add(position);
	}

	private List<String> definitions = new LinkedList<String>();

	private void execute(String definition) {
		definitions.add(definition);
		execute();
	}

	private void execute() {
		Workspace data = new Workspace("",positions, System.getProperty("user.dir"));
		BatchConsole console = new BatchConsole();
		data.console = console;
		shell.execute(data, definitions);
		output = console.output();
	}
}