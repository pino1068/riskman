package riskman.splitter;

import riskman.money.*;

public interface SplitCheckListener {

	void success(Object group, Ratio ratio);

	void failure(Object group, Ratio ratio);

}
