package riskman.command;

public class RemoveFilterCommand extends BaseCommand {

	public String describe() {
		return "	'r or remove'			- removes last filter";
	}

	public void execute(String definition) {
		definition = commands.contentOf(definition);
		String last = workspace.name();
		if(workspace.isNotRoot()){
			workspace.remove();
			delegateTo("positions");
			println("Removed last filter: "+last);
		}else{
			println("Sorry! No filter found!");
		}
	}

	private void delegateTo(String line) {
		Command command = commands.build(line);
		command.workspace(workspace);
		command.execute(line);
	}

}
