package ch.siagile.finance.app;


public class NullMenu extends BaseMenu {

	public boolean canExecute(String line) {
		return true;
	}

	public void execute(Workspace context, String line) {
		println(line + "- command not found. Are you kidding me?");
	}

	public String describe() {
		return "";
	}

}
