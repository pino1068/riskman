package riskman.repository;

import java.io.*;
import java.util.*;

public class TextRepository {

	public List<String> load(String pathname) {
		return scan(scanning(new File(pathname)));
	}

	public List<String> load(InputStream inputStream) {
		return scan(new Scanner(inputStream).useDelimiter("\n"));
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
			throw new RuntimeException(e);
		}
	}
}
