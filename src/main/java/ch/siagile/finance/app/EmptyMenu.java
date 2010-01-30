package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class EmptyMenu extends BaseMenu {

	public boolean isCalled(String line) {
		return line == null || "".equals(line.replaceAll(" ", ""));
	}

	public void perform(Positions positions, String line) {
		println("and now what?");
	}

	@Override
	public String describe() {
		return "";
	}

}
