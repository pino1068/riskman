package ch.siagile.finance.repository;


public class Factory {

	public static Repository repository() {
		return new CsvRepository();
	}

}