package ch.siagile.finance.app;


public class HelpMenu extends BaseMenu {

	public boolean isCalled(String line) {
		return line.startsWith("h");
	}

	public void perform(AppContext context, String line) {
		println("Menu: --------------------");
		for (Menu menu : parent().list()) {
			String description = menu.describe();
			if(!"".equals(description))
				println(description);
		}
		println("--------------------------");
	}
	
	@Override
	public String describe() {
		return "	'h' or 'help'			- print this help";
	}

}
