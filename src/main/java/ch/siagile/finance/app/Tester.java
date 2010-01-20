package ch.siagile.finance.app;

import static java.text.MessageFormat.*;

import java.util.*;

import ch.siagile.finance.position.*;
import ch.siagile.finance.repository.*;

public class Tester {

	private String inputpath;
	private String outputpath = "output.txt";
	private String dirname;

	public Tester(String[] args) {
		this.inputpath = args[0];
		this.dirname = dirname(inputpath);
	}

	private String dirname(String path) {
		return inputpath.substring(0, path.lastIndexOf("/"));
	}

	public static final void main(String[] args) {
		new Tester(args).test();
	}

	public void test() {
		List<String> definitions = new TextRepository().load(inputpath);
		List<String> expectedOutput = new TextRepository().load(format("{0}/{1}", dirname, outputpath));
		Shell shell = new Shell();
		Positions positions = new Positions();
		String output = shell.execute(dirname, positions, definitions);
		List<String> actualOutput = new StringRepository().load(output);
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < expectedOutput.size(); i++) {
			String expected = expectedOutput.get(i).trim();
			String actual = actualOutput.get(i).trim();
			if(expected.equals(actual)) {
				builder.append(".");
				System.out.println(format("S {0}", expected)); 
			}
			else {
				builder.append("F");
				System.out.println(format("F {0} got {1}", expected, actual));
			}
		}
		
		System.out.println(format("{0} {1}", builder.toString(), inputpath));
	}
}
