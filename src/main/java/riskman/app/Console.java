package riskman.app;

public interface Console {

	void println(String... string);

	void print(String... string);

	String waitForInput();

}
