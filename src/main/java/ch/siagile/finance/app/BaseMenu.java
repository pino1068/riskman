package ch.siagile.finance.app;

public abstract class BaseMenu implements Menu {

	protected Workspace workspace;

	public void workspace(Workspace workspace) {
		this.workspace = workspace;
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
