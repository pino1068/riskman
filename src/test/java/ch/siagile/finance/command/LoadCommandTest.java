package ch.siagile.finance.command;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static ch.siagile.finance.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.position.*;

public class LoadCommandTest {
	
	@Test 
	public void shouldLoadPortfolioCsv() {
		Positions positions = new Positions();
		new LoadCommand("load:src/test/resources/portfolio1.csv").execute(System.getProperty("user.dir"), positions);
		assertThat(positions, hasItem((Position) account("EUR-0456-389835-82", EUR(1041801.62494558))));
	}
	
	@Test 
	public void shouldLoadPortfolioCsvWithComments() {
		Positions positions = new Positions();
		String output = new LoadCommand("load:src/test/resources/portfolio-with-unknown-position.csv").execute(System.getProperty("user.dir"), positions);
		assertThat(output, containsString("load:src/test/resources/portfolio-with-unknown-position.csv KO"));
		assertThat(output, containsString("warning:,,,,,,,,,,,,,,,,,,,,,"));
	}

}
