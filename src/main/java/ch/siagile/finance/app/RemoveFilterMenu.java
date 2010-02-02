package ch.siagile.finance.app;


public class RemoveFilterMenu extends BaseMenu {

	public String describe() {
		return "	'r or remove'			- removes last filter";
	}

	public boolean canExecute(String line) {
		return line.startsWith("r");
	}

	public void execute(ContextData context, String line) {
		String last = context.name();
		if(context.isNotRoot()){
			context.remove();
			new PositionMenu().execute(context, line);
			println("Removed last filter: "+last);
		}else{
			println("Sorry man! No filter found!");
		}
	}

}
