package ch.siagile.finance.app;

import static ch.siagile.finance.fixtures.Fixtures.*;
import static ch.siagile.finance.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import ch.siagile.finance.instrument.*;
import ch.siagile.finance.matcher.*;
import ch.siagile.finance.position.*;

public class AppTest {
	private AppContext context;
	private Positions positions;

	@Before
	public void setUp() {
		positions = new Positions();
		positions.add(equity("ciao", 10, CHF(10)));
		positions.add(bond(Bond.from("bond1", "CH"), USD(1000), "100%"));
		positions.add(bond(Bond.from("bond2", "CH"), USD(1000), "100%"));
		context = new AppContext("", positions);
	}

	@Test
	public void shouldCreateContext() {
		assertThat(context.name(), is(equalTo("")));
	}

	@Test
	public void shouldContextHavePositionsStored() {
		assertThat(context.positions().size(), is(3));
	}

	@Test
	public void shouldChangeContext() {
		context.changeTo("bond",filter("bond"));
		assertThat(context.positions().size(), is(2));
		assertThat(context.name(), is("bond"));
	}

	private Positions filter(String def) {
		return positions.select(FilterBuilder.from(def));
	}

	@Test 
	public void shouldBackToParent() {
		context.changeTo("bond",filter("bond"));
		assertThat(context.positions().size(), is(2));
		assertThat(context.name(), is("bond"));
		context.remove();
		assertThat(context.positions().size(), is(3));
		assertThat(context.name(), is(""));
	}
	
}
