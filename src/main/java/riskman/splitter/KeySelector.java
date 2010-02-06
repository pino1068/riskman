package riskman.splitter;

import riskman.position.*;

public interface KeySelector {

	Object selectKey(Position position);

	boolean isCalled(String string);

}
