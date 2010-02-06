package ch.siagile.finance.app;

import static ch.siagile.finance.Some.*;
import static java.text.MessageFormat.*;

import java.io.*;
import java.util.*;

public class Tester extends Execute {

	private final List<String> expectedOutput;

	public Tester(String[] args, PrintStream printStream) {
		super(args, printStream);
		expectedOutput = textRepository.load(format("{0}/{1}", workingDir, outputpath));
	}

	public void test() {
		Workspace data = new Workspace(positions, workingDir);
		BatchConsole console = new BatchConsole();
		data.console = console;
		execute(data , definitions);

		String output = console.output();
		List<String> actualOutput = stringRepository.load(output);

		StringBuilder summary = compare(expectedOutput, actualOutput);

		info(format("{0} {1}", summary.toString(), inputpath));
	}

	public String execute(Workspace data, List<String> definitions) {
		List<String> outputs = new LinkedList<String>();
		for (String definition : definitions)
			try {
				BatchConsole console = new BatchConsole();
				data.console = console;
				shell.execute(data, definition);
				outputs.add(definition + console.output());
			} catch (Exception e) {
				e.printStackTrace();
				return format("error:{0} {1}", definition, e.getMessage());
			}
		return some(outputs).toString();
	}

	private StringBuilder compare(List<String> expectedOutput, List<String> actualOutput) {
		StringBuilder summary = new StringBuilder();
		for (int i = 0; i < expectedOutput.size(); i++) {
			String expected = expectedOutput.get(i).trim();
			String actual = actualOutput.get(i).trim();
			if (expected.equals(actual)) {
				summary.append(".");
				info(format("S {0}", expected));
			} else {
				summary.append("F");
				info(format("F {0} got {1}", expected, actual));
			}
		}
		return summary;
	}
}