package ch.siagile.finance.app;

import java.util.*;

public class Commands {

	private List<Menu> list;

	public Commands(Menu...menu) {
		list = new LinkedList<Menu>();
		for (Menu item : menu) 
			list.add(item);
	}

	public List<Menu> list() {
		return list;
	}
}
