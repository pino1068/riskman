package ch.siagile.finance.app;


public class HelpMenu extends BaseMenu {

	public boolean canExecute(String line) {
		return line.startsWith("h");
	}

	public void execute(ContextData context, String line) {
		println("Menu: --------------------");
		for (Menu menu : context.commands().list()) {
			String description = menu.describe();
			if(!"".equals(description))
				println(description);
		}
		println("--------------------------");
	}
	
	public String describe() {
		return "	'h' or 'help'			- print this help";
	}

}
