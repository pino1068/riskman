package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class App {
	private final static Commands commands = new Commands(new HelpMenu(),
			new PositionMenu(), new ExecutionMenu(), new FilterMenu(), new SplitByMenu(), new RemoveFilterMenu(), new QuitMenu(),
			new EmptyMenu(), new NullMenu());
	private final Console console;

	public App(Console console) {
		this.console = console;
	}

	public static void main(String args[]) {
		new App(new ShellConsole()).start();
	}

	public void start() {
		Workspace workspace = new Workspace(new Positions(), commands, System.getProperty("user.dir"));
		workspace.console = console;
		for (Menu command : commands.list()){
			command.workspace(workspace);
		}

		printHeader();
		String line = null;
		do {
			printIntro(workspace);
			line = console.waitForInput();
			for (Menu command : commands.list()) {
				if (command.canExecute(line)) {
					execute(command,workspace, line);
					break;
				}
			}
		} while (!new QuitMenu().canExecute(line));
	}

	private void execute(Menu command, Workspace data, String line) {
		try {
			command.execute(data, line);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printIntro(Workspace data) {
		console.println("");
		console.print(data.path());
		console.print(" > ");
	}

	private void printHeader() {
		for (int i = 0; i < 10; i++) 
			console.println("");
		console.println("Enter a command or 'h' for help or 'quit' to exit");
	}
}
