package ch.siagile.finance.app;


public interface Menu {

	boolean canExecute(String definition);

	void execute(ContextData data, String definition);

	String describe();
}
