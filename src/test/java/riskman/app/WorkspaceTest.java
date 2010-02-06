package riskman.app;

import static riskman.fixtures.Fixtures.*;
import static riskman.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.instrument.*;
import riskman.matcher.*;
import riskman.position.*;

public class WorkspaceTest {
	private Workspace workspace;
	private Positions positions;

	@Before
	public void setUp() {
		positions = new Positions();
		positions.add(equity("ciao", 10, CHF(10)));
		positions.add(bond(Bond.from("bond1", "CH"), USD(1000), "100%"));
		positions.add(bond(Bond.from("bond2", "CH"), USD(1000), "100%"));
		workspace = new Workspace("", positions, "");
	}

	@Test 
	public void shouldCreateContext() {
		assertThat(workspace.name(), is(equalTo("")));
	}

	@Test 
	public void shouldContextHavePositionsStored() {
		assertThat(workspace.positions().size(), is(3));
	}

	@Test 
	public void shouldChangeContext() {
		workspace.changeTo("bond",filter("bond"));
		assertThat(workspace.positions().size(), is(2));
		assertThat(workspace.name(), is("bond"));
	}

	@Test  
	public void shouldBackToParent() {
		workspace.changeTo("bond",filter("bond"));
		assertThat(workspace.positions().size(), is(2));
		assertThat(workspace.name(), is("bond"));
		workspace.remove();
		assertThat(workspace.positions().size(), is(3));
		assertThat(workspace.name(), is(""));
	}

	private Positions filter(String def) {
		return positions.select(FilterBuilder.from(def));
	}
	
}
