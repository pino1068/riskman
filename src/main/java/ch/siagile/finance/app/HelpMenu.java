package ch.siagile.finance.app;


public class HelpMenu extends BaseMenu {

	public boolean canExecute(String line) {
		return line.startsWith("h");
	}

	public void execute(Workspace context, String line) {
		context.console.println("Menu: --------------------");
		for (Menu menu : context.commands().list()) {
			String description = menu.describe();
			if(!"".equals(description))
				context.console.println(description);
		}
		context.console.println("--------------------------");
	}
	
	public String describe() {
		return "	'h' or 'help'			- print this help";
	}

}
