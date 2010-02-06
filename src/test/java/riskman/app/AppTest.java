package riskman.app;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

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
		check("	'h' or 'help'			- print this help");
		check("	'p' or 'positions'		- print all positions");
//		check("	'exe:<constraint>'		- to apply a constraint and get the result printed");
//		check("	'exe:load:<file>'		- to load a file with positions and get warnings printed");
		check("	'filter:<criteria>'		- print positions filtered by given criteria");
		check("	'split:<criteria>'		- print the splitted groups by given criteria");
		check("	'check:<split>'			- split and check according to criteria and print results, i.e. splitby:owner max:5%");
		check("	'r or remove'			- removes last filter");
		check("	'q' or 'quit'			- to exit this program");
		check("	'load:<file>'			- load positions from given file");
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
		console.enter("filter:owner:pippo2");
		app.start();
		check("Positions filtered with criteria: <owner:pippo2>");
		check("1 position(s) selected over 1 with criteria <owner:pippo2>");
		check("owner:pippo2 > ");
	}

	private void check(String string) {
		assertThat(console.output(), containsString(string));
	}
}
