package riskman.app;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class RiskManTest {

	@Test 
	public void shouldRiskManTester() {
		RiskMan riskMan = new RiskMan();
		riskMan.app(new String[] { "tester" });
		assertThat(riskMan.command, is(equalTo("tester")));
	}

	@Test 
	public void shouldRiskManShellIsDefault() {
		RiskMan riskMan = new RiskMan();
		riskMan.app(new String[] {});
		assertThat(riskMan.command, is(equalTo("shell")));
	}

	@Test 
	public void shouldRiskManHelp() {
		RiskMan riskMan = new RiskMan();
		riskMan.app(new String[] {"help"});
		assertThat(riskMan.command, is(equalTo("help")));
	}

	@Test 
	public void shouldRiskManShell() {
		RiskMan riskMan = new RiskMan();
		riskMan.app(new String[] { "shell" });
		assertThat(riskMan.command, is(equalTo("shell")));
	}

	@Test 
	public void shouldRiskManShellWhenThereArentArgumentes() {
		RiskMan riskMan = new RiskMan();
		riskMan.app(new String[] {});
		assertThat(riskMan.command, is(equalTo("shell")));
	}

	@Test 
	public void shouldRiskManBatch() {
		RiskMan riskMan = new RiskMan();
		riskMan.app(new String[] { "sample/sample1/sample1.txt" });
		assertThat(riskMan.command, is(equalTo("batch")));
	}
}
