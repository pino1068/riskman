package ch.siagile.finance.app;

public abstract class BaseMenu implements Menu {

	protected void println(Object... string) {
		for (Object item : string) 
			System.out.println(item);
	}
	
	protected void print(Object... strings) {
		for (Object string : strings) 
			System.out.print(string);
	}

	protected void newLine() {
		println("");
	}
}
