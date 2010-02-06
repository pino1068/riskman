package ch.siagile.finance.app;

import static java.text.MessageFormat.*;

import java.security.*;

import ch.siagile.finance.command.*;
import ch.siagile.finance.position.*;

public class App {
	private final Console console;
	private final Commands commands;
	private final Workspace workspace;

	public static void main(String args[]) {
		new App(new ShellConsole()).start();
	}
	
	public App(Console console) {
		this.console = console;
		commands = new Commands();
		workspace = workspace(commands);
	}

	private Workspace workspace(Commands commands) {
		Workspace workspace = new Workspace(new Positions(), System.getProperty("user.dir"));
		workspace.console = console;
		return workspace;
	}

	public void start() {
		header();
		working();
	}

	private void header() {
		for (int i = 0; i < 10; i++) 
			console.println("");
		console.println("Enter a command or 'h' for help or 'quit' to exit");
	}

	private void working() {
		Command command = null;
		do {
			printShell(workspace);
			String line = console.waitForInput();
			command = command(line);
			execute(command,line);
		} while (keepGoing(command));
	}

	private void printShell(Workspace workspace) {
		console.println("");
		console.print(workspace.path());
		console.print(" > ");
	}
	
	private void execute(Command command, String line) {
		try {
			command.execute(line);
		} catch (InvalidParameterException e) {
			console.println(format("{0} command not found",line));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Command command(String line) {
		Command command = commands.build(line);
		command.workspace(workspace);
		return command;
	}

	private boolean keepGoing(Command command) {
		return !QuitCommand.class.isInstance(command);
	}
}
