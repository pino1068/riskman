package ch.siagile.finance.command;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static ch.siagile.finance.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.app.*;
import ch.siagile.finance.position.*;

public class LoadCommandTest {
	
	private Positions positions;
	private Command loadCommand;
	private BatchConsole console;

	@Before
	public void setUp(){
		positions = new Positions();
		loadCommand = new LoadCommand();
		Workspace workspace = new Workspace(positions,System.getProperty("user.dir"));
		console = new BatchConsole();
		workspace.console = console;
		loadCommand.workspace(workspace);
	}
	@Test 
	public void shouldLoadPortfolioCsv() {
		loadCommand.execute("src/test/resources/portfolio1.csv");
		assertThat(positions, hasItem((Position) account("EUR-0456-389835-82", EUR(1041801.62494558))));
	}
	
	@Test 
	public void shouldLoadPortfolioCsvWithComments() {
		loadCommand.execute("src/test/resources/portfolio-with-unknown-position.csv");
		assertThat(console.output(), containsString(" KO"));
		assertThat(console.output(), containsString("warning:,,,,,,,,,,,,,,,,,,,,,"));
	}
}
