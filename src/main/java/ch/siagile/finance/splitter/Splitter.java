package ch.siagile.finance.splitter;

import ch.siagile.finance.*;
import ch.siagile.finance.position.*;

public class Splitter {

	private final KeySelector selector;

	public Splitter(KeySelector selector) {
		this.selector = selector;
	}

	public Split split(Positions positions) {
		Split split = new Split();
		for (Position position : positions) {
			split.positions(selector.selectKey(position)).add(position);
		}
		return split;
	}

}
