package ch.siagile.finance.app;


public interface Menu {

	boolean canExecute(String definition);

	void execute(Workspace data, String definition);

	String describe();

	void workspace(Workspace workspace);
}
