package ch.siagile.finance;

import ch.siagile.finance.service.CsvRepository;

public class Factory {

	public static Repository repository() {
		return new CsvRepository();
	}

}