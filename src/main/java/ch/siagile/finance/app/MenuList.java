package ch.siagile.finance.app;

import java.util.*;

public class MenuList {

	private List<Menu> list;

	public MenuList(Menu...menu) {
		list = new LinkedList<Menu>();
		for (Menu item : menu) {
			item.parent(this);//just used by help menu item!
			list.add(item);
		}
	}

	public List<Menu> list() {
		return list;
	}
}
