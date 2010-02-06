package ch.siagile.finance.parser;

import ch.siagile.finance.position.*;

public interface OperationListener {

	public void success(String string, Position position);

	public void failure(String string);

}
