package ch.siagile.finance.app;


public class ExecutionMenu extends BaseMenu {

	public boolean canExecute(String definition) {
		return definition.startsWith("e");
	}
	
	public void execute(Workspace context, String definition) {
		context.console.println(new Shell().execute(context, clean(definition)));
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
