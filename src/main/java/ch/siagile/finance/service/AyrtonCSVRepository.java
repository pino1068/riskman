package ch.siagile.finance.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import ch.siagile.finance.Areas;
import ch.siagile.finance.Repository;

public class AyrtonCSVRepository implements Repository{

	public Areas allAreas() {
		Areas areas = new Areas();
		Scanner scanner = scanner();
		scanner.nextLine();//skip header
		while(scanner.hasNext()){
			areas.add(extractAreaName(scanner.next()));
		}
		return areas;
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
