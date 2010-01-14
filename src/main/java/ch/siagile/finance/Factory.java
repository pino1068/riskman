package ch.siagile.finance;

import ch.siagile.finance.service.AyrtonCSVRepository;

public class Factory {

	public static Repository repository() {
		return new AyrtonCSVRepository();
	}

}
