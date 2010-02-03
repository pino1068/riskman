package ch.siagile.finance.app;


public class EmptyMenu extends BaseMenu {

	public boolean canExecute(String line) {
		return line == null || "".equals(line.replaceAll(" ", ""));
	}

	public void execute(Workspace context, String line) {
		println("and now what?");
	}

	public String describe() {
		return "";
	}

}
