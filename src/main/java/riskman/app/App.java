package riskman.app;

import static riskman.app.Dirs.workingDir;

import java.io.File;
import java.security.InvalidParameterException;

import riskman.app.console.*;
import riskman.command.*;
import riskman.position.Positions;

public class App {
	private Console console;
	private final Commands commands;
	private final Workspace workspace;
	private static String sessionFilename;
	private static App app;

	public static void main(String args[]) {
		App.create(console()).start();
	}

	static App create(Console console2) {
		app = new App(console2);
		return app;
	}

	public static void recordTo(String filename) {
		app.reportTo(filename);
	}

	private void reportTo(String filename) {
		if (!console.canRestart())
			return;
		System.out.println("recording to..." + filename);
		sessionFilename = filename;
		this.console = console();
		workspace.console = this.console;
	}

	private static Console console() {
		return commands(session(shell()), commandFile());
	}

	private static Console shell() {
		return new ShellConsole();
	}

	private static File commandFile() {
		return new File("commands_" + filenamePostfix());
	}

	private static String filenamePostfix() {
		if (sessionFilename == null)
			sessionFilename = System.currentTimeMillis() + ".txt";
		return sessionFilename;
	}

	private static Console commands(Console console, File commandFile) {
		return new CommandLoggingConsole(console, commandFile);
	}

	private static Console session(Console console) {
		return new SessionLoggingConsole(console, sessionFile());
	}

	private static File sessionFile() {
		return new File("session_" + filenamePostfix());
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
			noCommand(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void noCommand(String line) {
		final NoCommand noCommand = new NoCommand();
		noCommand.workspace(workspace);
		noCommand.execute(line);
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
