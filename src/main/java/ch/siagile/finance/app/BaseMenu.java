package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public abstract class BaseMenu implements Menu {

	private MenuList parent;

	public abstract boolean isCalled(String line);

	public abstract void perform(Positions positions, String line);

	public abstract String describe() ;

	public void parent(MenuList list) {
		this.parent = list;
	}
	
	protected void println(Object... string) {
		for (Object item : string) 
			System.out.println(item);
	}
	
	protected MenuList parent(){
		return parent;
	}
	
	protected void print(Object... strings) {
		for (Object string : strings) 
			System.out.print(string);
	}

	protected void newLine() {
		println("");
	}
}
