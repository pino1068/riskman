package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class ExecutionMenu extends BaseMenu {

	@Override
	public boolean isCalled(String line) {
		return line.startsWith("e");
	}
	
	@Override
	public void perform(Positions positions, String line) {
		Shell shell = new Shell();
		try {
			String definition = line; 
			definition = definition.replaceAll("exe:", "");
			definition = definition.replaceAll("e:", "");
			println(shell.execute(positions, definition));
		} catch (Exception e) {
			print("Error: ", e.getMessage(), "");
		}
	}

	@Override
	public String describe() {
		return "		'exe:<constraint>'		- to execute a constraint";
	}
}
