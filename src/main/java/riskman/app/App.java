package riskman.app;

import static java.text.MessageFormat.*;
import static riskman.app.Dirs.*;

import java.io.*;
import java.security.*;

import riskman.command.*;
import riskman.position.*;

public class App {
	private final Console console;
	private final Commands commands;
	private final Workspace workspace;

	public static void main(String args[]) {
		new App(console()).start();
	}

	private static Console console() {
		return new LoggingConsole(new ShellConsole(), new File(createSessionName()));
	}

	private static String createSessionName() {
		return "session_"+System.currentTimeMillis()+".txt";
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
