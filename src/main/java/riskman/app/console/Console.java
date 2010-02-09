package riskman.app.console;

public interface Console {

	void println(String... string);

	void print(String... string);

	String waitForInput();

}
