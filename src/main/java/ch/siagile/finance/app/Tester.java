package ch.siagile.finance.app;

import static java.text.MessageFormat.*;

import java.io.*;
import java.util.*;

import ch.siagile.finance.position.*;
import ch.siagile.finance.repository.*;

public class Tester {

	private String inputpath;
	private String outputpath = "output.txt";
	private String dirname;
	private List<String> definitions;
	private List<String> expectedOutput;
	private final PrintStream printStream;

	public Tester(String[] args, PrintStream printStream) {
		this.printStream = printStream;
		this.inputpath = args[1];
		this.dirname = dirname(inputpath);
		definitions = textRepository.load(inputpath);
		expectedOutput = textRepository.load(format("{0}/{1}", dirname, outputpath));
	}

	private String dirname(String path) {
		return path.substring(0, path.lastIndexOf("/"));
	}

	public static final void main(String[] args) {
		new Tester(args, System.out).test();
	}

	private final TextRepository textRepository = new TextRepository();
	private final StringRepository stringRepository = new StringRepository();
	private final Shell shell = new Shell();
	private final Positions positions = new Positions();

	public void test() {

		String output = shell.execute(dirname, positions, definitions);

		List<String> actualOutput = stringRepository.load(output);

		StringBuilder summary = compare(expectedOutput, actualOutput);

		info(format("{0} {1}", summary.toString(), inputpath));
	}

	private void info(String format) {
		printStream.println(format);
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