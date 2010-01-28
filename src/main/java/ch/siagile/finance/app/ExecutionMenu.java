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
			println(shell.execute("", positions, clean(line)));
		} catch (Exception e) {
			print("Error: ", e.getMessage(), "");
		}
	}

	private String clean(String line) {
		String definition = line; 
		definition = definition.replaceAll("exe:", "");
		definition = definition.replaceAll("e:", "");
		return definition;
	}

	@Override
	public String describe() {
		return "		'exe:<constraint>'		- to execute a constraint";
	}
}
