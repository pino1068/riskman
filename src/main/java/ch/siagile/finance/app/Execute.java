package ch.siagile.finance.app;

import java.io.*;
import java.util.*;

import ch.siagile.finance.position.*;
import ch.siagile.finance.repository.*;

public class Execute {

	protected final String inputpath;
	protected final String outputpath = "output.txt";
	protected final String dirname;
	protected final List<String> definitions;
	protected final PrintStream printStream;

	public Execute(String[] args, PrintStream printStream) {
		this.printStream = printStream;
		this.inputpath = inputPath(args);
		this.dirname = dirname(inputpath);
		definitions = textRepository.load(inputpath);
	}

	private String inputPath(String[] args) {
		if (args.length == 1) return args[0];
		return args[1];
	}

	private String dirname(String path) {
		return path.substring(0, path.lastIndexOf("/"));
	}

	protected final TextRepository textRepository = new TextRepository();
	protected final StringRepository stringRepository = new StringRepository();
	protected final Shell shell = new Shell();
	protected final Positions positions = new Positions();

	public void batch() {

		String output = shell.execute(dirname, positions, definitions);

		List<String> actualOutput = stringRepository.load(output);

		info(actualOutput);
	}

	private void info(List<String> strings) {
		for (String string : strings) {
			info(string);
		}
	}

	protected void info(String format) {
		printStream.println(format);
	}

}
