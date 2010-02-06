package riskman.app;

import java.util.*;

public class BatchConsole implements Console{
	private List<String> entries;
	private StringBuffer output;

	public BatchConsole() {
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
