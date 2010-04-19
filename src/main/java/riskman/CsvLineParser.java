package riskman;

public class CsvLineParser {

	protected final String[] fields;

	public CsvLineParser(String line) {
		fields = line.split(";");
	}

	protected String f(int fieldPosition) {
		return fields[fieldPosition];
	}

}
