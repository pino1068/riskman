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

	private void check(String string) {
		assertThat(console.output(), containsString(string));
	}
}
