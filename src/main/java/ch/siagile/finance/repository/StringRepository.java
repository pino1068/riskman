package ch.siagile.finance.repository;

import java.util.*;

public class StringRepository {

	public List<String> load(String pathname) {
		List<String> strings = new LinkedList<String>();

		Scanner scanner = scanning(pathname);

		while (scanner.hasNext()) {
			String string = scanner.next();
			if (isComment(string))
				continue;
			strings.add(string);
		}

		return strings;
	}

	private boolean isComment(String string) {
		return string.startsWith("#");
	}

	private Scanner scanning(String string) {
		return new Scanner(string).useDelimiter("\n");
	}

}
