package riskman.app;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import riskman.app.console.*;

public class AppTest {

	private BatchConsole console;
	private App app;

	@Before
	public void setUp() {
		console = new BatchConsole();
		app = new App(console);
	}

	@Test
	public void shouldStartApp() {
		app.start();
		check("Enter a command or 'h' for help or 'quit' to exit");
	}

	@Test
	public void shouldHelp() {
		console.enter("help");
		app.start();

		check("Menu: --------------------");
		check("print this help");
		check("print all positions");
		check("print positions filtered by given criteria");
		check("print the splitted groups by given criteria");
		check("split and check according to criteria and print results, i.e. splitby:owner max:5%");
		check("removes last filter");
		check("to exit this program");
		check("load positions from given file");
		check("--------------------------");
	}

	@Test
	public void shouldLoadPositions() {
		console.enter("load:src/test/resources/portfolio1.csv");
		app.start();
		check("Enter a command or 'h' for help or 'quit' to exit");
		check("OK");
	}

	@Test
	public void shouldShowPositions() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("positions");
		app.start();
		check("OK");
		check("Actual positions are:");
		check("pippo2:  Account: EUR-0456-389835-82");
		check("1 position(s) found!");
	}

	@Test
	public void shouldFilterByPippo2() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("filter:pippo2");
		app.start();
		check("Positions filtered with criteria: <pippo2>");
		check("1 position(s) selected over 1 with criteria <pippo2>");
		check("pippo2 > ");
	}

	@Test
	public void shouldFilterByPippo2WithSpace() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("filter: pippo2");
		app.start();
		check("Positions filtered with criteria: <pippo2>");
		check("1 position(s) selected over 1 with criteria <pippo2>");
		check("pippo2 > ");
	}

	@Test
	public void shouldFilterByPippo2WithSpaceBefore() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("filter :pippo2");
		app.start();
		check("Positions filtered with criteria: <pippo2>");
		check("1 position(s) selected over 1 with criteria <pippo2>");
		check("pippo2 > ");
	}

	@Test
	public void shouldFilterByPippo2WithMoreSpaces() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("filter :  pippo2");
		app.start();
		check("Positions filtered with criteria: <pippo2>");
		check("1 position(s) selected over 1 with criteria <pippo2>");
		check("pippo2 > ");
	}

	@Test
	public void shouldFilterByPippo2AndMoreSpaces() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("filter :  pippo2 ,pippo3");
		app.start();
		check("Positions filtered with criteria: <pippo2,pippo3>");
		check("1 position(s) selected over 1 with criteria <pippo2,pippo3>");
		check("pippo2,pippo3 > ");
	}

	@Test
	public void shouldShowListAreas() {
		console.enter("show: areas");
		app.start();
		check("Available areas:");
		check("CH");
		check("GBP");
		check("NORD AMERICA");
		check("PACIFICO");
		check("UE");
		check("USA");
		check("6 element(s) found.");
	}

	@Test
	public void shouldShowListOwners() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("show: owners");
		app.start();
		check("Available owners:");
		check("pippo2");
		check("1 element(s) found.");
	}

	@Test
	public void shouldShowEquities() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("show: equities");
		app.start();
		check("Available equities:");
		check("Philips");
		check("1 element(s) found.");
	}

	@Test
	public void shouldShowBonds() {
		console.enter("load:src/test/resources/portfolio-Table1.csv");
		console.enter("show: bonds");
		app.start();
		check("Available bonds:");
		check("20111130");
		check("176 element(s) found.");
	}
	
	@Test
	public void shouldShowMoodys() {
		console.enter("load:src/test/resources/portfolio-Table1.csv");
		console.enter("show: moody");
		app.start();
		check("Available moody:");
		check("Aaa");
		check("20 element(s) found.");
	}

	@Test
	public void shouldLoadFromRoot() {
		console.enter("load:DBPortfolioGenerale.csv");
		app.start();
		check("260 positions loaded over 268 lines read.");
	}

	private void check(String string) {
		assertThat(console.output(), containsString(string));
	}
}
