package riskman.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static riskman.money.Money.CHF;
import static riskman.money.Money.EUR;
import static riskman.money.Money.USD;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import riskman.app.console.BatchConsole;
import riskman.money.ExchangeRate;
import riskman.money.ExchangeRates;

public class AppTest {

	private BatchConsole console;
	private App app;

	@Before
	public void setUp() {
		console = new BatchConsole();
		app = App.create(console);
		ExchangeRates.clear();
		ExchangeRates.addRate(ExchangeRate.rateFrom(CHF(1), CHF(1)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(USD(1), CHF(1.1)));
		ExchangeRates.addRate(ExchangeRate.rateFrom(EUR(1), CHF(1.6)));
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
		check("split and check according to criteria and print results");
		check("removes last filter");
		check("to exit this program");
		check("load positions from given file");
		check("--------------------------");
	}
	
	@Test 
	public void shouldShortcutHelp() {
		console.enter("h");
		app.start();
		
		check("Menu: --------------------");
		check("print this help");
	}
	

	@Test
	public void shouldLoadPositions() {
		console.enter("load:src/test/resources/portfolio1.csv");
		app.start();
		check("Enter a command or 'h' for help or 'quit' to exit");
		check("OK");
	}

	@Test
	public void shouldTooShortcut() {
		console.enter("s:owner");
		app.start();
		check("Enter a command or 'h' for help or 'quit' to exit");
		check("command not found");
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
	public void shouldShortcut1ShowPositions() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("p");
		app.start();
		check("Actual positions are:");
		check("pippo2:  Account: EUR-0456-389835-82");
		check("1 position(s) found!");
	}
	
	@Test 
	public void shouldShortcut2ShowPositions() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("po");
		app.start();
		check("Actual positions are:");
		check("pippo2:  Account: EUR-0456-389835-82");
		check("1 position(s) found!");
	}
	
	@Test 
	public void shouldShortcut3ShowPositions() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("pos");
		app.start();
		check("Actual positions are:");
		check("pippo2:  Account: EUR-0456-389835-82");
		check("1 position(s) found!");
	}
	
	@Test 
	public void shouldShortcut5ShowPositions() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("posit");
		app.start();
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
	public void shouldShortcutShowListOwners() {
		console.enter("load:src/test/resources/portfolio1.csv");
		console.enter("sh:owners");
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
		check("842 element(s) found.");
	}

	@Test
	public void shouldShowBonds() {
		console.enter("load:src/test/resources/portfolio-Table1.csv");
		
		console.enter("show: bonds");
		app.start();
		
		check("Available bonds:");
		check("element(s) found.");
	}

	/*
	 * I don't understand why this test fails in eclipse and doesn't fail
	 * on the shell "mvn clean test"!!
	 */
	@Test @Ignore
	public void shouldShowBonds_STRANGE_BEHAVIOUR() {
		console.enter("load:src/test/resources/portfolio-Table1.csv");
		console.enter("show: bonds");
		app.start();
		check("Available bonds:");

		check("20101011 - 1.002% EIB FRN 11-10-10 Pro-rata rating:NR");
		check("20101011 - 1.002% EIB FRN 11-10-10 rating:NR");
		check("20120209 - 3.375% Shell Finance 09-02-12 Pro-rata rating:NR");
		check("20120209 - 3.375% Shell Finance 09-02-12 rating:NR");
		check("20120607 - 5.375% Resau Ferre De France 07-06-12 Pro-rata rating:NR");
		check("20120607 - 5.375% Resau Ferre De France 07-06-12 rating:NR");
		check("20130624 - 5.75% National Australia Bank 24-06-13 Pro-rata rating:NR");
		check("20130624 - 5.75% National Australia Bank 24-06-13 rating:NR");

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
	
	@Test 
	public void shouldrunPositionCommandWhenEmpty() {
		console.enter("positions");
		
		app.start();
		
		check("Currently there are no positions available");
	}
	

	@Test
	public void shouldDeletePositions() {
		console.enter("load:DBPortfolioGenerale.csv");
		console.enter("delete");
		console.enter("positions");
		
		app.start();
		
		check("Currently there are no positions available");
	}
	

	@Test
	public void shouldRecordToCommand() {
		console.enter("recordTo:pippo.txt");
		
		app.start();
		
		check("from now all your commands will be reported to <pippo.txt>");
	}

	private void check(String string) {
		assertThat(console.output(), containsString(string));
	}
	
	@Test
	public void shouldLoadExchangeRatesCommand() {
		console.enter("load-exchange-rates:Cambi_out.csv");
		
		app.start();
		
		check("Imported 30 exchange rates:");
		check("Exchange from 1 EUR to 1.424 CHF");
	}
	
	@Test
	public void shouldStartWebServer() {
		console.enter("web");
		
		app.start();
		
		check("web server started on port 8080. Try with http://localhost:8080");
	}
	
	@Test
	public void shouldStartWebServerTwice() {
		console.enter("web");
		console.enter("web");
		
		app.start();
		
		check("The web server is already running on port 8080...");
	}
}
