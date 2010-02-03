package ch.siagile.finance.app;


public class QuitMenu extends BaseMenu {

	public boolean canExecute(String line) {
		return line.startsWith("q");
	}
	
	public void execute(Workspace context, String line) {
	}

	public String describe() {
		return "\n	'q' or 'quit'			- to exit this program";
	}
}
