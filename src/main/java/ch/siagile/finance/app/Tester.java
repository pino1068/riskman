package ch.siagile.finance.app;

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
		String output = shell.execute(data , definitions);

		List<String> actualOutput = stringRepository.load(output);

		StringBuilder summary = compare(expectedOutput, actualOutput);

		info(format("{0} {1}", summary.toString(), inputpath));
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