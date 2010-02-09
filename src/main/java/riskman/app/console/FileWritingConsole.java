package riskman.app.console;

import java.io.*;

public abstract class FileWritingConsole implements Console{

	protected final Console delegate;
	protected final PrintStream file;

	public FileWritingConsole(Console delegate, File file) {
		this.delegate = delegate;
		this.file = printStream(file);
	}

	private PrintStream printStream(File file) {
		PrintStream result = null;
		try {
			result = new PrintStream(file);
		} catch (FileNotFoundException e) {
			this.delegate.println("not logging on file: "+file);
			e.printStackTrace();
		}
		return result;
	}

	public String waitForInput() {
		String input = delegate.waitForInput();
		file.println(input);
		return input;
	}

}
