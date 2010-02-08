package riskman.app;

import java.io.*;

public class CommandLoggingConsole extends FileWritingConsole{

	public CommandLoggingConsole(Console delegate, File file) {
		super(delegate, file);
	}

	public void print(String... strings) {
		delegate.print(strings);
	}

	public void println(String... strings) {
		delegate.println(strings);
	}
}
