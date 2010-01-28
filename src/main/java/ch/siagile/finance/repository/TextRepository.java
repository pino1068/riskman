package ch.siagile.finance.repository;

import static java.text.MessageFormat.*;

import java.io.*;
import java.util.*;

public class TextRepository {

	public List<String> load(String pathname) {
		System.out.println(format("reading file {0}",pathname));
		return scan(scanning(new File(pathname)));
	}

	public List<String> load(InputStream resourceAsStream) {
		return scan(new Scanner(resourceAsStream).useDelimiter("\n"));
	}

	private List<String> scan(Scanner scanner) {
		List<String> strings = new LinkedList<String>();
		while(scanner.hasNext()) {
			String string = scanner.next();
			if(isComment(string)) continue;
			strings.add(string);
		}
		return strings;
	}

	private boolean isComment(String string) {
		return string.startsWith("#");
	}

	private Scanner scanning(File file) {
		try {
			return new Scanner(file).useDelimiter("\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("boh, file not found",e);
		}
	}
}
