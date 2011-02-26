package riskman.app.console;

public interface Console {

	void println(String...strings);

	void print(String...strings);

	String waitForInput();

	boolean canRestart();

}
