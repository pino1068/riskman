package ch.siagile.finance.app;

import org.junit.*;

public class TesterTest {
	
	@Test
	public void shouldTester() {
		new Tester(new String[] {"sample/sample1/sample1.txt"}).test();
	}
}
