package ch.siagile.finance.repository;

import java.io.*;
import java.util.*;

import ch.siagile.finance.location.*;

public class CsvRepository implements Repository{

	public Areas allAreas() {
		Areas areas = new Areas();
		Scanner scanner = scanner();
		skipHeader(scanner);
		while(scanner.hasNext()){
			areas.add(extractAreaName(scanner.next()));
		}
		return areas;
	}

	private void skipHeader(Scanner scanner) {
		scanner.nextLine();
	}

	private String extractAreaName(String line) {
		return line.split(",")[1];
	}

	private static Scanner scanner() {
		try {
			return new Scanner(new FileReader("src/test/resources/areas.csv")).useDelimiter("\n");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}