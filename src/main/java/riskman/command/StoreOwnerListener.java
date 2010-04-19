package riskman.command;

import riskman.*;
import riskman.parser.*;
import riskman.position.*;

public class StoreOwnerListener implements OperationListener {

	private final OwnerRepository repository = new OwnerRepository();

	public StoreOwnerListener() {
		repository.cleanup();
	}
	
	public void failure(String string) {
	}

	public void success(String string, Position position) {
		repository.add(position.owner());
	}

}
