package ch.siagile.finance.app;


public class RemoveFilterMenu extends BaseMenu {

	public String describe() {
		return "	'r or remove'			- removes last filter";
	}

	public boolean isCalled(String line) {
		return line.startsWith("r");
	}

	public void perform(AppContext context, String line) {
		String last = context.name();
		if(context.isNotRoot()){
			context.remove();
			new PositionMenu().perform(context, line);
			println("Removed last filter: "+last);
		}else{
			println("Sorry man! No filter found!");
		}
	}

}
