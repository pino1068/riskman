package riskman;

import static org.junit.Assert.*;

import org.junit.*;

import riskman.app.*;
import riskman.parser.*;
import riskman.position.*;


public class FirstRegressionTest {

	private Positions positions;

	@Before
	public void setUp() {
		positions = new Positions();
	}

	@Test 
	public void shouldRegression1() {
		position("pippo2,L,EUR,1041801.62494558,,EUR-0456-389835-82,,,1041801.62494558,1041801.62494558,,A,Saldi in Cto. Corrente,1537366.08235009,1041801.62494558,1510924.89665857,,,,,,");
		check("CHF max:20%");
	}

	@Test 
	public void shouldRegression2() {
		try {
			position("pippo8,KPRD,EUR,180,1335786EU,Sparinvest Sicav Global  Value,105.019996643066,140.4,25272,37293.391278,0.336888254502429,E,Azioni e simili,37293.391278,25272,36651.9816,1841,14,,,,");
			fail("expected exception for not implemented position parser - retrived are: "+positions);
		} catch (Exception e) {
		}
	}

	private void check(String definition) {
		Workspace data = new Workspace("",positions, System.getProperty("user.dir"));
		new Shell().execute(data, definition);
	}

	private void position(String string) {
		positions.add(new PositionsParser().parse(string));
	}
}
