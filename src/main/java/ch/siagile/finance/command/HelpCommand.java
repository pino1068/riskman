package ch.siagile.finance.command;




public class HelpCommand extends BaseCommand {

	public void execute(String line) {
		println("Menu: --------------------");
		for (Command menu : commands.list()) {
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
