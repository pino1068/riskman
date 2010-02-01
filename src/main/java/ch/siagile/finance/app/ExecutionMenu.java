package ch.siagile.finance.app;


public class ExecutionMenu extends BaseMenu {

	@Override
	public boolean isCalled(String line) {
		return line.startsWith("e");
	}
	
	@Override
	public void perform(AppContext context, String line) {
		Shell shell = new Shell();
		try {
			println(shell.execute("", context.positions(), clean(line)));
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
		return 	"	'exe:<constraint>'		- to apply a constraint and get the result printed"
		+ 		"\n	'exe:load:<file>'		- to load a file with positions and get warnings printed";
	}
}
