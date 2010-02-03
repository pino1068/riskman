package ch.siagile.finance.app;

import java.util.*;

public class TestConsole implements Console{
	private List<String> entries;
	private StringBuffer output;

	public TestConsole() {
		entries = new LinkedList<String>();
		output = new StringBuffer();
	}
	public void enter(String string) {
		entries.add(string);
	}

	public String output() {
		return output.toString();
	}
	
	public void print(String... strings) {
		for (String string : strings) 
			output.append(string);
	}

	public void println(String... strings) {
		for (String string : strings) 
			output.append(string).append("\n");
	}

	public String waitForInput() {
		if(entries.isEmpty())
			return "quit";
		return entries.remove(0);
	}

}
