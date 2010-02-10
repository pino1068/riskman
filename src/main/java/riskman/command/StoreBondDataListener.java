package riskman.command;

import riskman.parser.OperationListener;
import riskman.position.*;
import riskman.repository.BondRepository;

public class StoreBondDataListener implements OperationListener {

	private final OperationListener listener;
	private final BondRepository repository;

	public StoreBondDataListener(OperationListener listener) {
		this.listener = listener;
		repository = new BondRepository();
	}

	public void failure(String string) {
		listener.failure(string);

	}

	public void success(String string, Position position) {
		listener.success(string, position);
		if(BondPosition.class.isInstance(position)){
			repository.add(toBondPosition(position).bond());
		}
	}

	private BondPosition toBondPosition(Position position) {
		return (BondPosition)position;
	}
}

