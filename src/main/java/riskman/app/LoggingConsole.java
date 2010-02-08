package riskman.app;

import java.io.*;

public class LoggingConsole implements Console {

	private final Console targetConsole;
	private final PrintStream printStream;

	public LoggingConsole(Console targetConsole, File file) {
		this.targetConsole = targetConsole;
		this.printStream = printStream(file);
	}

	private PrintStream printStream(File file) {
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(file);
		} catch (FileNotFoundException e) {
			this.targetConsole.println("not logging on file: "+file);
			e.printStackTrace();
		}
		return printStream;
	}

	public void print(String... strings) {
		targetConsole.print(strings);
		for (String string : strings)
			printStream.print(string);
	}

	public void println(String... strings) {
		targetConsole.println(strings);
		for (String string : strings)
			printStream.println(string);
	}

	public String waitForInput() {
		final String input = targetConsole.waitForInput();
		printStream.println(input);
		return input;
	}

}
