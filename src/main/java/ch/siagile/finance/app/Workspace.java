package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class Workspace {

	public Console console;

	private WorkspaceData current;
	private Commands commands;
	private String workingDir;

	public Workspace(String name, Positions positions, Commands commands, String workingData) {
		this.current = new WorkspaceData(name,positions);
		this.commands = commands;
		this.workingDir = workingData;
	}

	public Workspace(Positions positions, Commands commands, String workingDir) {
		this("", positions, commands, workingDir);
	}

	public Workspace(Positions positions, String workingDir) {
		this("", positions, null, workingDir);
	}

	public String name() {
		return current.name();
	}

	public Positions positions() {
		return current.positions();
	}
	
	public void remove() {
		this.current = current.previous();
	}

	public void changeTo(String name, Positions positions) {
		this.current = new WorkspaceData(name, positions, current);
	}

	public boolean isNotRoot() {
		return current.hasPrevious();
	}

	public String path() {
		return current.path();
	}

	public Commands commands() {
		return commands;
	}

	public String workingDir() {
		return workingDir;
	}
}
