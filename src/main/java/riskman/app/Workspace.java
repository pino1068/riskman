package riskman.app;


import riskman.app.console.*;
import riskman.position.*;

public class Workspace {

	public Console console = new BatchConsole();
	private WorkspaceData current;
	private String workingDir;

	public Workspace(String name, Positions positions, String workingData) {
		this.current = new WorkspaceData(name,positions);
		this.workingDir = workingData;
	}

	public Workspace(Positions positions, String workingDir) {
		this("", positions, workingDir);
	}

	public String name() {
		return current.name();
	}

	public Positions positions() {
		return current.positions().sortByName();
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

	public String workingDir() {
		return workingDir;
	}

	public void addAll(Positions positionsToAdd) {
		current.positions().addAll(positionsToAdd);
	}
}
