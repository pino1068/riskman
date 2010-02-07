package riskman.parser;

import riskman.position.*;

public interface OperationListener {

	public void success(String string, Position position);

	public void failure(String string);

}
