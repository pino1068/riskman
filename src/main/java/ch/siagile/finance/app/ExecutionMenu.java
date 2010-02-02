package ch.siagile.finance.app;


public class ExecutionMenu extends BaseMenu {

	public boolean canExecute(String definition) {
		return definition.startsWith("e");
	}
	
	public void execute(ContextData context, String definition) {
		Shell shell = new Shell();
		try {
			println(shell.execute(context, clean(definition)));
		} catch (Exception e) {
			print("Error: ", e.getMessage(), "");
		}
	}

	private String clean(String definition) {
		String result = definition; 
		result = result.replaceAll("exe:", "");
		result = result.replaceAll("e:", "");
		return result;
	}

	public String describe() {
		return 	"	'exe:<constraint>'		- to apply a constraint and get the result printed"
		+ 		"\n	'exe:load:<file>'		- to load a file with positions and get warnings printed";
	}
}
