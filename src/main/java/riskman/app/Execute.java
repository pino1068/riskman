package riskman.app;

import java.io.*;
import java.util.*;

import riskman.position.*;
import riskman.repository.*;

public class Execute {

	protected final String inputpath;
	protected final String outputpath = "output.txt";
	protected final String workingDir;
	protected final List<String> definitions;
	protected final PrintStream printStream;

	public Execute(String[] args, PrintStream printStream) {
		this.printStream = printStream;
		this.inputpath = inputPath(args);
		this.workingDir = dirname(inputpath);
		definitions = textRepository.load(inputpath);
	}

	private String inputPath(String[] args) {
		if (args.length == 1) return args[0];
		return args[1];
	}

	private String dirname(String path) {
		System.out.println(path);
		if(!path.contains("/"))
			return "./";
		return path.substring(0, path.lastIndexOf("/"));
	}

	protected final TextRepository textRepository = new TextRepository();
	protected final StringRepository stringRepository = new StringRepository();
	protected final Shell shell = new Shell();
	protected final Positions positions = new Positions();

	public void batch() {
		Workspace data = new Workspace(positions, workingDir);
		BatchConsole console = new BatchConsole();
		data.console = console;
		shell.execute(data, definitions);

		List<String> actualOutput = stringRepository.load(console.output());

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
