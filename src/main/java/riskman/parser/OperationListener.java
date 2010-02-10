package riskman.parser;

import riskman.position.*;

public interface OperationListener {

	void success(String string, Position position);

	void failure(String string);

}
