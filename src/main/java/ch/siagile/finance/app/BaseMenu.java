package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public abstract class BaseMenu implements Menu {

	private MenuList parent;

	public boolean isCalled(String line) {
		return false;
	}

	public void perform(Positions positions, String line) {
	}
	
	protected void println(String... string) {
		for (String item : string) {
			System.out.println(item);
		}
	}
	
	protected MenuList parent(){
		return parent;
	}
	
	protected void print(String... strings) {
		for (String string : strings) {
			System.out.print(string);
		}
	}

	public void parent(MenuList list) {
		this.parent = list;
	}


	public String describe() {
		return "";
	}


}
