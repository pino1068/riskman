package ch.siagile.finance.splitter;

import ch.siagile.finance.position.*;

public interface KeySelector {

	Object selectKey(Position position);

	boolean isCalled(String string);

}
