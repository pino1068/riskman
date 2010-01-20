package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class NullMenu extends BaseMenu {

	public boolean isCalled(String line) {
		return true;
	}

	public void perform(Positions positions, String line) {
		println(line + "- command not found. Are you kidding with me?");
	}

}
