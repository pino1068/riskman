package ch.siagile.finance.app;

import java.io.*;

import org.junit.*;

public class BatchTest {

	private final PrintStream out = new PrintStream(new ByteArrayOutputStream());

	@Test 
	public void shouldExecute() {
		new Execute(new String[] { "batch", "sample/sample1/sample1.txt" }, out).batch();
	}

	@Test 
	public void shouldExecuteWithoutCommandName() {
		new Execute(new String[] { "sample/sample1/sample1.txt" }, out).batch();
	}
}
