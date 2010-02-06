package ch.siagile.finance.app;

import java.io.*;

import org.junit.*;

public class TesterTest {
	
	@Test  @Ignore
	public void shouldTester() {
                PrintStream out = new PrintStream(new ByteArrayOutputStream());
		new Tester(new String[] {"tester", "sample/sample1/sample1.txt"}, out).test();
	}
}
