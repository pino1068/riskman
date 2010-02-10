package riskman.command;

import static java.text.MessageFormat.format;
import riskman.app.*;


public abstract class BaseCommand implements Command {

	protected Workspace workspace;
	protected Commands commands;

	public void init(Commands commands) {
		this.commands = commands;
	}
	
	public void workspace(Workspace workspace) {
		this.workspace = workspace;
	}
	protected void printlnFormatted(String pattern, Object ... data) {
		println(format(pattern,data));
	}
	protected void println(Object... thinks) {
		for (Object think : thinks) 
			workspace.console.println(think.toString());
	}
	
	protected void print(Object... thinks) {
		for (Object think : thinks) 
			workspace.console.print(think.toString());
	}

	protected void newLine() {
		println("");
	}
}