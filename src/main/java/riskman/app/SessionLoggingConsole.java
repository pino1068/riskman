package riskman.app;

import java.io.*;

public class SessionLoggingConsole extends FileWritingConsole{

	public SessionLoggingConsole(Console delegate, File file) {
		super(delegate, file);
	}

	public void print(String... strings) {
		delegate.print(strings);
		for (String string : strings)
			file.print(string);
	}

	public void println(String... strings) {
		delegate.println(strings);
		for (String string : strings)
			file.println(string);
	}
}
