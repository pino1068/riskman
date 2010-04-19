package riskman.parser;

public class CsvLineParser {

	protected final String[] fields;

	public CsvLineParser(String line) {
		fields = line.split(";");
	}

	protected String f(int fieldPosition) {
		if (fieldPosition >= fields.length)
			return "";
		return fields[fieldPosition];
	}

}
