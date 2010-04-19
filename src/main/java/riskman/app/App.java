package riskman.app;

import static java.text.MessageFormat.format;
import static riskman.app.Dirs.workingDir;

import java.io.File;
import java.security.InvalidParameterException;

import riskman.app.console.*;
import riskman.command.*;
import riskman.position.Positions;

public class App {
	private final Console console;
	private final Commands commands;
	private final Workspace workspace;

	public static void main(String args[]) {
		new App(console()).start();
	}

	private static Console console() {
		return commands(session(shell()), commandFile());
	}

	private static Console shell() {
		return new ShellConsole();
	}

	private static File commandFile() {
		return new File("commands_" + System.currentTimeMillis() + ".txt");
	}

	private static Console commands(Console session, File commandFile) {
		return new CommandLoggingConsole(session, commandFile);
	}

	private static Console session(Console console) {
		return new SessionLoggingConsole(console, sessionFile());
	}

	private static File sessionFile() {
		return new File("session_" + System.currentTimeMillis() + ".txt");
	}

	public App(Console console) {
		this.console = console;
		commands = new Commands();
		workspace = workspace(commands);
	}

	private Workspace workspace(Commands commands) {
		Workspace workspace = new Workspace(new Positions(), workingDir());
		workspace.console = console;
		return workspace;
	}

	public void start() {
		header();
		working();
	}

	private void header() {
		console.println("Enter a command or 'h' for help or 'quit' to exit");
	}

	private void working() {
		Command command = null;
		do {
			printShell(workspace);
			String line = console.waitForInput();
			command = command(line);
			execute(command, line);
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
			console.println(format("{0} command not found", line));
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
