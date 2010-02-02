package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class ContextData {

	private AppContextData current;
	private Commands commands;
	private String workingDir;

	public ContextData(String name, Positions positions, Commands commands, String workingData) {
		this.current = new AppContextData(name,positions);
		this.commands = commands;
		this.workingDir = workingData;
	}

	public ContextData(Positions positions, Commands commands, String workingDir) {
		this("", positions, commands, workingDir);
	}

	public ContextData(Positions positions, String workingDir) {
		this("", positions, null, workingDir);
	}

	public String name() {
		return current.name();
	}

	public Positions positions() {
		return current.positions();
	}
	
	public void remove() {
		this.current = current.parent();
	}

	public void changeTo(String name, Positions positions) {
		this.current = new AppContextData(name, positions, current);
	}

	public boolean isNotRoot() {
		return current.hasParent();
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
