package riskman.command;

import static riskman.fixtures.Fixtures.*;
import static riskman.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.*;
import riskman.app.*;
import riskman.app.console.*;
import riskman.position.*;

import static riskman.app.Dirs.*;

public class LoadCommandTest {
	
	private Positions positions;
	private Command loadCommand;
	private BatchConsole console;
	private OwnerRepository ownerRepository;

	@Before
	public void setUp(){
		positions = new Positions();
		loadCommand = new LoadCommand();
		Workspace workspace = new Workspace(positions,workingDir());
		console = new BatchConsole();
		workspace.console = console;
		loadCommand.workspace(workspace);
		ownerRepository = new OwnerRepository();
		ownerRepository.cleanup();
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
		assertThat(console.output(), containsString("warning:Unrecognized string: ,,,,,,,,,,,,,,,,,,,,,"));
	}
	
	@Test 
	public void shouldStoreOnwerInRepository() {
		assertFalse(ownerRepository.contains("pippo2"));
		
		loadCommand.execute("src/test/resources/portfolio1.csv");
		
		assertTrue(ownerRepository.contains("pippo2"));
	}
	
	@Test
	public void shouldLoadFromRoot() {
		loadCommand.execute("DBPortfolioGenerale.csv");
		assertThat(console.output(), containsString(" KO"));
		assertThat(console.output(), containsString("warning:Unrecognized string: pippo10;PS;CHF;500000.0;"));
	}

}
