package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class QuitMenu extends BaseMenu {

	@Override
	public boolean isCalled(String line) {
		return line.startsWith("q");
	}
	
	@Override
	public void perform(Positions positions, String line) {
	}

	@Override
	public String describe() {
		return "		'q' or 'quit'			- to exit this program";
	}
}
