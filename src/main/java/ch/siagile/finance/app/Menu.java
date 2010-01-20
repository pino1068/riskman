package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public interface Menu {

	boolean isCalled(String line);

	void perform(Positions positions, String line);

	void parent(MenuList menuList);

	String describe();

}
