package riskman.app;


import org.mortbay.jetty.Server;

import riskman.app.console.*;
import riskman.position.Positions;

public class Workspace {

	public Console console = new BatchConsole();
	private WorkspaceData current;
	private String workingDir;
	public Server server;

	public Workspace(String name, Positions positions, String workingDir) {
		this.current = new WorkspaceData(name,positions);
		this.workingDir = workingDir;
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

	public Positions clearAll() {
		final Positions positions = positions();
		this.current = new WorkspaceData("",new Positions());
		return positions;
	}

	public void release() {
		try {
			if (server != null){
				server.stop();
				console.println("...web server stopped!");
			}
		} catch (Exception e) {
			console.println(e.getMessage());
		}
	}

	public Workspace copyTo(Console console) {
		Workspace workspace = new Workspace("web server", current.positions(), workingDir);
		workspace.console = console;
		return workspace;
	}
}
