package ch.siagile.finance.app;


public class QuitMenu extends BaseMenu {

	@Override
	public boolean isCalled(String line) {
		return line.startsWith("q");
	}
	
	@Override
	public void perform(AppContext context, String line) {
	}

	@Override
	public String describe() {
		return "\n	'q' or 'quit'			- to exit this program";
	}
}
