package ch.siagile.finance.splitter;

import ch.siagile.finance.money.*;

public interface SplitCheckListener {

	void success(Object group, Ratio ratio);

	void failure(Object group, Ratio ratio);

}
